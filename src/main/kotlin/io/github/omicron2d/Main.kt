package io.github.omicron2d

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import de.tudresden.inf.lat.jsexp.SexpFactory
import io.github.omicron2d.communication.messages.IncomingInitMessage
import org.tinylog.kotlin.Logger
import java.io.File
import java.io.FileInputStream
import java.util.*
import kotlin.concurrent.thread
import kotlin.system.exitProcess
import kotlin.system.measureTimeMillis

/*
 * Notes:
 * I think the best way to arrange the Main is to have it do what it's supposed to, start a single agent.
 * Then make a file called like TestRunner.kt which runs the agents and tools and everything separately.
 * We would also have to write a shell script to launch the agent using an embedded JVM for at the venue.
 * Maybe use java packager with the jvm for that to reduce build size. We can worry about that if we get to it.
 */

class Omicron2DApp : CliktCommand(){
    private val startServer by option(help="Start an instance of rcssserver").flag()
    private val startMonitor by option(help="Start an instance of rcssmonitor").flag()
    private val startOpposition by option(help="Starts a team of sample clients to play against").flag()
    private val muteTools by option(help="Mute the output of rcssserver and rcssmonitor").flag()

    override fun run() {
        var server: Process? = null
        var monitor: Process? = null
        val opposition: Process? = null

        // we register the shutdown hook up in here in case of any exceptions that would cause it not to be created
        Runtime.getRuntime().addShutdownHook(thread(start = false) {
            println("Shutting down sub-processes...")
            opposition?.destroy() // damn right
            server?.destroy()
            monitor?.destroy()
        })

        if (muteTools){
            Logger.info("Tools (rcssserver and rcssmonitor) are being muted.")
        }

        // start server if requested
        if (startServer){
            Logger.info("Starting rcssserver...")
            server = ProcessBuilder().apply {
                directory(File("../../../logs/"))
                command("rcssserver")
                if (!muteTools) inheritIO()
            }.start()
            Thread.sleep(1500) // wait for it to start
        }

        if (startMonitor){
            Logger.info("Starting monitor...")
            monitor = ProcessBuilder().apply {
                command("soccerwindow2")
                if (!muteTools) inheritIO()
            }.start()

            // if the user closes the monitor, quit the app as well
            monitor?.onExit()?.thenAccept {
                Logger.info("Monitor has terminated! Shutting down...")
                exitProcess(0)
            }
        }

        // TODO only quit if both debugger and monitor are closed

        // TODO start agent here with new framework

        // TODO start 11 agent2d's (or another team's binary) here with support for running either left side or right side
    }
}

object Main {
    @JvmStatic
    fun main(args: Array<String>){
        System.setProperty("log4j.configuration", "log4j.properties")
        System.setProperty("tinylog.configuration", "tinylog.properties")
        System.setProperty("kryo.unsafe", "false")

        Logger.info("Omicron2D v${VERSION}: Copyright (c) 2019-2020 Matt Young. Available under the MPL 2.0.")
        // TODO bring this back in
        // Omicron2DApp().main(args)

        val config = Properties()
        config.load(FileInputStream("general.properties"))

        // TODO just a parsing test
        val testInitMessage = "(init l 5 goal_kick_l)"
        val msg = IncomingInitMessage().deserialise(testInitMessage)

//        val time = measureTimeMillis {
//            for (i in 0 until 100) {
//                val msg = IncomingInitMessage().deserialise(testInitMessage)
//            }
//        }
//        println("AVERAGE TIME: ${time / 100} ms")
    }
}