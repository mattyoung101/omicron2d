/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.communication.messages

/**
 * Base class for all messages that can be serialised to, or deserialised from the soccer server
 */
interface ServerMessage

/**
 * Outgoing server message: transmitted from the client to the server
 */
interface OutgoingServerMessage : ServerMessage {
    /**
     * Serialises this message to an S-expression for transmission to the soccer server.
     */
    fun serialise(): String
}

/**
 * Incoming server message: transmitted from the server to the client
 */
interface IncomingServerMessage : ServerMessage {
    // this class has no methods, instead see IncomingMessageDeserialiser which they are all expected to implement
}

/**
 * Interface for all companion objects of IncomingServerMessage classes that can deserialise server S-expressions
 */
interface IncomingMessageDeserialiser {
    /**
     * Deserialises the given S-expression and returns a new instance of the respective IncomingServerMessage
     * @param input the S-expression, as pretty much received directly from the server
     */
    fun deserialise(input: String): IncomingServerMessage
}