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
        val sexString = "(sense_body 0 (view_mode high normal) (stamina 8000.12345 1 130600) (speed 0 0) (head_angle 0) " +
                "(kick 0) (dash 0) (turn 0) (say 0) (turn_neck 0) (catch 0) (move 0) (change_view 0) (arm (movable 0) (expires 0) " +
                "(target 0 0) (count 0)) (focus (target none) (count 0)) (tackle (expires 0) (count 0)) (collision none) (test \"()))\"))"
        val sexString2 = "(see 0 ((f c t) 21.1 8 0 0) ((f r t) 73.7 2) ((f r b) 102.5 44) ((f g r b) 85.6 31) ((g r) 82.3 27) " +
                "((f g r t) 79 22) ((f p r c) 68 33) ((f p r t) 59.1 16) ((f t 0) 21.1 -5 0 0) ((f t r 10) 31.2 -4) ((f t r 20) 40.9 -3) " +
                "((f t r 30) 50.9 -2) ((f t r 40) 60.9 -2) ((f t r 50) 70.8 -2) ((f t l 10) 11.1 -10 0 0) ((F) 2.2 -63) ((f r 0) 86.5 25) " +
                "((f r t 10) 83.1 19) ((f r t 20) 80.6 12) ((f r t 30) 79 5) ((f r b 10) 91.8 31) ((f r b 20) 96.5 36) ((f r b 30) 103.5 40))"
        val sex2 = "(begin \"string()\" 0.12345 1.0 1)"
        val parsed = SexpFactory.parse(sex2)

        val testInitMessage = "(init l 22345)"
        val msg = IncomingInitMessage().deserialise(testInitMessage)
    }
}