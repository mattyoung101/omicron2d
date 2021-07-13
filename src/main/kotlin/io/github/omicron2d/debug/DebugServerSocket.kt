/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2021 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.debug

import org.tinylog.kotlin.Logger
import javax.websocket.OnClose
import javax.websocket.OnOpen
import javax.websocket.Session
import javax.websocket.server.ServerEndpoint

@ServerEndpoint(value = "/agents/")
open class DebugServerSocket {
    @OnOpen
    fun onOpen(session: Session){
        Logger.debug("Websocket client connected!")
        DebugServer.clients.add(session)
    }

    @OnClose
    fun onClose(session: Session){
        Logger.debug("Websocket client disconnected!")
        DebugServer.clients.remove(session)
    }
}