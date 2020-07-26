package io.github.omicron2d.communication

import java.net.InetAddress

class PlayerAgent(host: InetAddress = InetAddress.getLocalHost(), port: Int = 6000) : SoccerAgent(host, port) {
    private var lastMessageTime = System.currentTimeMillis()

    override fun onReceiveMessage(message: String) {
        // we would go and parse it here

        // TODO read:
        // so the way this works, is that PlayerAgent should run on the main thread
        // we will use a LinkedBlockingQueue which the network thread will post to, then we wait for it here

        println("(last: ${System.currentTimeMillis() - lastMessageTime}) $message")
        lastMessageTime = System.currentTimeMillis()
    }

    // make event loop shit here with queue
    // also consider making all the queue stuff in the SoccerAgent, abstract it out
    // TODO consider using event bus????
    // hash map of parsers?
}