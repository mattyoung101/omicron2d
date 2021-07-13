/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2021 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.debug

import com.google.gson.GsonBuilder
import io.github.omicron2d.utils.CURRENT_CONFIG
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.ServerConnector
import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.websocket.javax.server.config.JavaxWebSocketServletContainerInitializer
import org.tinylog.kotlin.Logger
import java.io.IOException
import javax.servlet.ServletContext
import javax.websocket.Session
import javax.websocket.server.ServerContainer

/** Debug WebSocket server using an embedded Jetty */
object DebugServer {
    private val server = Server()
    val clients = mutableListOf<Session>()
    private val gson = GsonBuilder().serializeSpecialFloatingPointValues().create()

    /** Launches the debug server, unless it was already started */
    fun maybeLaunchServer(){
        if (server.isStarting || server.isStarted) return

        // source:
        // https://github.com/jetty-project/embedded-jetty-websocket-examples/blob/10.0.x/javax.websocket-example/src/main/java/org/eclipse/jetty/demo/EventServer.java
        val connector = ServerConnector(server)
        connector.port = CURRENT_CONFIG.get().debugServerPort
        server.addConnector(connector)
        Logger.info("Starting debug server on port ${connector.port}")

        // Setup the basic application "context" for this application at "/"
        // This is also known as the handler tree (in jetty speak)
        val context = ServletContextHandler(ServletContextHandler.SESSIONS)
        context.contextPath = "/"
        server.handler = context

        try {
            // Initialize javax.websocket layer
            JavaxWebSocketServletContainerInitializer.configure(context) { _: ServletContext?, wsContainer: ServerContainer ->
                // This lambda will be called at the appropriate place in the
                // ServletContext initialization phase where you can initialize
                // and configure  your websocket container.

                // Configure defaults for container
                wsContainer.defaultMaxTextMessageBufferSize = 65535

                // Add WebSocket endpoint to javax.websocket layer
                wsContainer.addEndpoint(DebugServerSocket::class.java)
            }

            server.start()
        } catch (e: Exception) {
            Logger.warn("Exception in debug server:")
            Logger.warn(e)
        }
    }

    /** Stops the server */
    fun stopServer(){
        server.stop()
    }

    /**
     * Transmits the message with code [msgId] and payload [obj] to the server.
     */
    fun transmit(msgId: String, agentId: Int, obj: Any){
        if (!CURRENT_CONFIG.get().enableDebugServer) return

        val message = mapOf(Pair("msgId", msgId), Pair("agentId", agentId), Pair("payload", obj))
        val serialised = gson.toJson(message)

        for (client in clients){
            try {
                client.basicRemote.sendText(serialised)
            } catch (e: IOException){
                Logger.warn("Failed to transmit to client: $client, error: $e")
                // TODO don't remove immediately?
                clients.remove(client)
            }
        }
    }
}