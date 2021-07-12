/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2021 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.debug

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.ServerConnector
import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.websocket.javax.server.config.JavaxWebSocketServletContainerInitializer
import org.tinylog.kotlin.Logger
import javax.servlet.ServletContext
import javax.websocket.server.ServerContainer


/** Debug WebSocket server */
object DebugServer {
    private val server = Server()

    fun launchServer(){
        // source:
        // https://github.com/jetty-project/embedded-jetty-websocket-examples/blob/10.0.x/javax.websocket-example/src/main/java/org/eclipse/jetty/demo/EventServer.java
        val connector = ServerConnector(server)
        connector.port = 8080 // TODO make a define for this!
        server.addConnector(connector)

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
                // TODO
                //wsContainer.addEndpoint(EventSocket::class.java)
            }

            server.start()
            server.join()
        } catch (e: Exception) {
            Logger.warn("Exception in debug server:")
            Logger.warn(e)
        }
    }

    fun stopServer(){
        server.stop()
    }
}