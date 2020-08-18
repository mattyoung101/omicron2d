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
        // 3 minute timer to ensure the socket stays connected
        soTimeout = 300000
    }
    private val sockThread = thread(start = false, name = "SocketThread"){
        Logger.debug("Socket thread started")

        while (true){
            if (Thread.interrupted()) {
                Logger.debug("Socket thread interrupted, quitting")
                return@thread
            }

            val buf = ByteArray(8192)
            val packet = DatagramPacket(buf, buf.size)
            socket.receive(packet)
            // TODO do we need to make sure the packet is actually coming from the correct socket server somehow?

            val messageBytes = packet.data.takeWhile { it != 0.toByte() }.toByteArray()
            val messageString = messageBytes.toString(charset = Charset.forName("US-ASCII"))
            Logger.trace("Inbound message: $messageString")
            onReceiveMessage(messageString)
        }
    }
    val eventQueue = LinkedBlockingQueue<String>()

    /**
     * Called when a message has been received from network.
     * Note: this runs in the context of the socket thread.
     */
    abstract fun onReceiveMessage(message: String)

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
    }

    /**
     * Waits for the socket thread to exit
     */
    fun await(){
        sockThread.join()
    }

    /**
     * Closes the socket and cleans up resources
     */
    fun disconnect(){
        if (!isConnected){
            Logger.warn("Tried to disconnect an agent that is not connected")
            return
        }
        // otherwise, hit the disconnect
        transmitString("(bye)")
        socket.close()
        sockThread.interrupt()
        isConnected = false
    }
}