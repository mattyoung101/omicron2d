/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.communication

import io.github.omicron2d.communication.messages.OutgoingInitMessage
import io.github.omicron2d.communication.messages.OutgoingServerMessage
import io.github.omicron2d.utils.CURRENT_CONFIG
import org.tinylog.kotlin.Logger
import java.net.*
import java.nio.charset.Charset
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.LinkedBlockingQueue
import kotlin.concurrent.thread

/**
 * Abstract class for all intelligent agents on the soccer simulation server, for example, players and coaches.
 * Also serves as the base for Omicron2D's networking implementation.
 *
 * The reference for this is page 71 of the manual (section 6)
 *
 * Also based on AbstractUDPClient from the atan Java rcssserver library
 * @param host IP address of rcssserver
 * @param defaultPort default port of server, will be switched to server assigned one later
 */
abstract class AbstractSoccerAgent(private var host: InetAddress, private var defaultPort: Int) {
    private var isConnected = false
    private val socket = DatagramSocket().apply {
        // 10 second timer to ensure the socket stays connected (inherited from atan)
        soTimeout = CURRENT_CONFIG.get().timeout
    }
    /**
     * Port to respond to, instead of the default port (init port), once we've received a response from rcssserver.
     *
     * The docs are extremely unclear, but it appears that in versions 8+ you're not allowed to send anything but init
     * on the default port, and you have to switch over to the port that rcssserver replies to you with otherwise it
     * gives you (error only_init_allowed_on_init_port). For reference, do a search in rcssserver project for that error:
     *
     * `~/workspace/rcssserver-16.0.0$ rg -i only_init_allowed_on_init_port`
     */
    private var respondTo: Int? = null
    /** Contains the queue of messages received from the server, that are awaiting processing */
    val messages = LinkedBlockingQueue<String>()
    /** Queue of individual messages that can be batched together and transmitted as one string to rcsserver */
    private val messageBatch = ConcurrentLinkedQueue<OutgoingServerMessage>()

    private val sockThread = thread(start=false, name="ReceiveThread"){
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
                // if the read was interrupted and we should be terminating, just quit
                if (Thread.interrupted()){
                    println("Ignoring socket exception due to thread interrupt (quitting!)")
                    return@thread
                }

                // otherwise, log the error
                Logger.error("Failed to receive():")
                Logger.error(e)
            } catch (e: SocketTimeoutException){
                Logger.error("Timeout during receive(), server probably offline:")
                Logger.error(e)

                // simple teardown routine since calling disconnect() doesn't work in this context
                isConnected = false
                respondTo = null
                teardown()
                socket.close()
                messages.add("INTERNAL_TIMED_OUT")
                println("Socket thread finished due to timeout")
                return@thread
            }

            // switch to new port to avoid error (see respondTo description)
            if (respondTo == null){
                Logger.info("Switching to new port ${packet.port}")
                respondTo = packet.port
            }

            val messageBytes = packet.data.takeWhile { it != 0.toByte() }.toByteArray()
            val messageString = messageBytes.toString(charset = Charset.forName("US-ASCII"))
            Logger.trace("Incoming message (from ${packet.address}:${packet.port}): $messageString")

            messages.add(messageString)
            //println("Queue size: ${messages.size}")
        }
    }

    /**
     * Main loop of agent, usually should wait for a message to become available in the queue and then process it
     */
    abstract fun run()

    /**
     * Called when the agent is being shutdown. May be called more than once. Default implementation does nothing.
     */
    open fun teardown(){}

    /**
     * Internal method to transmit a string to the remote server over UDP with ASCII encoding. Generally you want to use
     * transmit(). The string provided will have a null terminator added for transport.
     */
    protected fun transmitString(str: String){
        if (!isConnected){
            throw IllegalStateException("Tried to send message on unconnected socket")
        }
        Logger.trace("Outgoing message (to ${host}:${respondTo ?: defaultPort}): $str")

        // write null terminated string
        // source: https://stackoverflow.com/a/17737242/5007892
        val b = str.toByteArray(Charset.forName("US-ASCII"))
        val bytes = ByteArray(b.size + 1)
        System.arraycopy(b, 0, bytes, 0, b.size)

        val packet = DatagramPacket(bytes, bytes.size, host, respondTo ?: defaultPort)
        socket.send(packet)
    }

    /**
     * Serialises then transmits a message to the server
     */
    fun transmit(message: OutgoingServerMessage){
        transmitString(message.serialise())
    }

    /**
     * Concatenates all messages into one string, and then transmits the bunch
     */
    fun transmit(messages: Iterable<OutgoingServerMessage>){
        val text = messages.joinToString("") { it.serialise() }
        transmitString(text)
    }

    /**
     * Concatenates all messages into one string, and then transmits the bunch
     */
    fun transmit(messages: Array<OutgoingServerMessage>){
        val text = messages.joinToString("") { it.serialise() }
        transmitString(text)
    }

    /** Adds the specified message to the current message batch. NOT THREAD SAFE! */
    fun pushBatch(message: OutgoingServerMessage){
        messageBatch.add(message)
    }

    /** Sends the current batch to the server and clears it for new items. NOT THREAD SAFE! */
    fun flushBatch(){
        transmit(messageBatch)
        messageBatch.clear()
    }

    /** Clears the current message batch. NOT THREAD SAFE! */
    fun clearBatch(){
        messageBatch.clear()
    }

    /**
     * Starts socket thread and sends init message to server
     * @param uselessMode special parameter for test usage, if true, does not start socket thread
     */
    fun connect(initMessage: OutgoingInitMessage, uselessMode: Boolean = false){
        isConnected = true
        transmit(initMessage)
        if (uselessMode) return
        sockThread.start()

        Runtime.getRuntime().addShutdownHook(thread(start=false){
            if (isConnected){
                println("Shutdown hook is cleaning up agent")
                disconnect()
            }
        })
    }

    /**
     * Closes the socket and cleans up resources. This method may block for up to 600 ms.
     */
    fun disconnect(){
        if (!isConnected){
            Logger.warn("Tried to disconnect an agent that is not connected")
            return
        }

        // tell the server we're disconnecting (attempt to shut down gracefully)
        println("Disconnecting agent")
        transmitString("(bye)")
        teardown()
        Thread.sleep(100) // wait for transmission and teardown

        // close down the socket
        sockThread.interrupt()
        sockThread.join(500)
        socket.close()
        println("Socket thread joined!")

        isConnected = false
        respondTo = null
    }
}