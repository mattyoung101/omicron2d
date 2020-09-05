/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.communication

import io.github.omicron2d.communication.messages.OutgoingServerMessage
import org.greenrobot.eventbus.EventBus
import org.tinylog.kotlin.Logger
import java.net.*
import java.nio.charset.Charset
import java.util.concurrent.LinkedBlockingQueue
import kotlin.concurrent.thread

/**
 * Abstract class for all intelligent agents on the soccer simulation server, for example, players and coaches.
 * The reference for this is page 71 of the manual (section 6)
 * Also based on AbstractUDPClient from the atan Java rcssserver library
 */
abstract class SoccerAgent(private var host: InetAddress, private var port: Int) {
    private var isConnected = false
    private val socket = DatagramSocket().apply {
        // 3 minute timer to ensure the socket stays connected (inherited from atan)
        soTimeout = 300000
    }
    val messages = LinkedBlockingQueue<String>()

    private val sockThread = thread(start = false){
        Logger.debug("Socket thread started")

        while (true){
            if (Thread.interrupted()) {
                Logger.debug("Socket thread interrupted, quitting")
                return@thread
            }

            val buf = ByteArray(8192)
            val packet = DatagramPacket(buf, buf.size)
            try {
                socket.receive(packet)
            } catch (e: SocketException){
                Logger.error("Failed to receive from server socket:")
                Logger.error(e)
            } catch (e: SocketTimeoutException){
                Logger.warn("Timeout during receive! Server may have gone offline.")
                Logger.warn(e)
                // TODO do we want to disconnect here?
            }

            val messageBytes = packet.data.takeWhile { it != 0.toByte() }.toByteArray()
            val messageString = messageBytes.toString(charset = Charset.forName("US-ASCII"))
            Logger.trace("Inbound message: $messageString")

            messages.add(messageString)
            //println("Queue size: ${messages.size}")
        }
    }

    /**
     * Main loop of agent, usually should wait for a message to become available in the queue and then process it
     */
    abstract fun run()

    /**
     * Internal method to transmit a string to the remote server over UDP with ASCII encoding
     */
    private fun transmitString(str: String){
        Logger.trace("Outbound message: $str")
        val bytes = str.toByteArray(Charset.forName("US-ASCII"))
        val packet = DatagramPacket(bytes, bytes.size, host, port)
        socket.send(packet)
    }

    /**
     * Serialises then transmits a message to the server
     */
    fun transmit(message: OutgoingServerMessage){
        if (!isConnected){
            throw IllegalStateException("Tried to send message on unconnected socket")
        }
        transmitString(message.serialise())
    }

    /**
     * Starts socket thread and sends init message to server
     */
    fun connect(initMessage: OutgoingServerMessage){
        isConnected = true
        transmit(initMessage)
        sockThread.start()

        Runtime.getRuntime().addShutdownHook(thread(start=false){
            if (isConnected){
                println("Shutdown hook is cleaning up agent")
                disconnect()
            }
        })
    }

    /**
     * Closes the socket and cleans up resources. This method may block for up to 500 ms.
     */
    fun disconnect(){
        if (!isConnected){
            Logger.warn("Tried to disconnect an agent that is not connected")
            return
        }

        // otherwise, hit the disconnect
        Logger.debug("Disconnecting agent")
        transmitString("(bye)")
        sockThread.interrupt()
        sockThread.join(500)
        socket.close()
        isConnected = false
    }
}