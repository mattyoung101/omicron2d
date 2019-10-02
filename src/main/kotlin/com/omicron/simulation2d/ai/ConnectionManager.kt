package com.omicron.simulation2d.ai

import com.omicron.simulation2d.Connection

/**
 * Note that the connection manager is NOT thread safe. As we run one agent per thread, each agent needs its own
 * connection manager.
 */
class ConnectionManager {
    /**
     * map of connections: id to connection for faster lookup (saves iterating through an array),
     * should be close to O(1) *
     **/
    private val connections = hashMapOf<Int, Connection>()
    private var id = 0

    /**
     * Establishes a new connection with another agent
     */
    fun newConnection(): Connection {
        return Connection(id++)
    }
}