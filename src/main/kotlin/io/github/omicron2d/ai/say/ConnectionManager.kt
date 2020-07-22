package io.github.omicron2d.ai.say

import com.esotericsoftware.kryo.Kryo
import com.github.robocup_atan.atan.model.ActionsPlayer
import io.github.omicron2d.Connection
import io.github.omicron2d.AgentMessage
import org.tinylog.kotlin.Logger

/**
 * Note that the connection manager is NOT thread safe. As we run one agent per thread, each agent needs its own
 * connection manager.
 */
class ConnectionManager(kryo: Kryo) {
    // map of connections, ID to connection instance, used for faster lookup
    private val connections = hashMapOf<Int, Connection>()
    private var id = 0
    private val messageTransformer = MessageTransformer(kryo)

    /** Establishes a new connection with another agent **/
    fun newConnection(): Connection {
        val connection = Connection(id++)
        connections[connection.id] = connection
        Logger.trace("Established a new connection with auto id $id")
        return connection
    }

    private fun newWithId(id: Int): Connection {
        val conn = Connection(id)
        if (id in connections){
            Logger.error("Illegal connection, ID $id already exists! Returning that one.")
            return connections[id]!!
        }
        connections[conn.id] = conn
        Logger.trace("Established a new connection with manual id $id")
        return conn
    }

    /** Returns a Connection stored for the given id or makes a new one if it doesn't exist **/
    fun getConnection(id: Int): Connection {
        if (id !in connections){
            Logger.error("Connection id $id doesn't exist, returning a new one")
            return newWithId(id)
        }
        return connections[id]!!
    }

    /** Destroys a previously established connection with another agent **/
    fun destroyConnection(connection: Connection){
        Logger.trace("Destroying connection id ${connection.id}")
        // TODO may need to inform remote that connection has been destroyed
        connections.remove(connection.id)
    }

    /** Transmits a message to another agent. Automatically sets remoteId to the correct number and says via ActionsPlayer **/
    fun send(message: AgentMessage, connection: Connection, actions: ActionsPlayer?){
        message.remoteId = connection.id
        val msg = messageTransformer.encode(message)
        println("Transmitting message: $msg")
        actions?.say(msg)
        connection.sent.add(message)
    }

    /** Decodes a received message and finds it a connection **/
    fun receive(msg: String): Connection? {
        val message = messageTransformer.decode(msg) ?: return null
        val connection = if (message.remoteId !in connections){
            Logger.warn("ID ${message.remoteId} is not yet an established connection, creating a new one")
            newWithId(message.remoteId)
        } else {
            connections[message.remoteId]!!
        }
        connection.received.add(message)
        return connection
    }
}