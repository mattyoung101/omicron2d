package com.omicron.simulation2d

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.int
import com.omicron.simulation2d.tools.Debugger
import javafx.application.Application
import org.tinylog.kotlin.Logger
import java.io.File
import kotlin.concurrent.thread
import kotlin.system.exitProcess

class Omicron2DApp : CliktCommand(){
    private val startServer by option(help="Start an instance of rcssserver").flag()
    private val startMonitor by option(help="Start an instance of rcssmonitor").flag()
    private val startOpposition by option(help="Starts a team of sample clients to play against").flag()
    private val muteTools by option(help="Mute the output of rcssserver and rcssmonitor").flag()
    private val debugger by option(help="Start the Omicron2D debugging application").flag()

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
                command("rcssmonitor")
                if (!muteTools) inheritIO()
            }.start()

            // if the user closes the monitor, quit the app as well
            monitor?.onExit()?.thenAccept {
                Logger.info("Monitor has terminated! Shutting down...")
                exitProcess(0)
            }
        }

        // run the debugger
        if (debugger){
            Logger.info("Starting Omicron2D Visual Debugger...")
            thread {
                Application.launch(Debugger::class.java, "")
                Logger.debug("Visual Debugger has been closed, shutting down!")
                exitProcess(0)
            }
        }

        // TODO only quit if both debugger and monitor are closed

        // run our team
        Omicron2DTeam().connectAll()

        // TODO start 11 agent2d's (or another team's binary) here with support for running either left side or right side
    }
}

object Main {
    @JvmStatic
    fun main(args: Array<String>){
        System.setProperty("log4j.configuration", "log4j.properties")
        System.setProperty("tinylog.configuration", "tinylog.properties")
        System.setProperty("kryo.unsafe", "false")

        Logger.info("Omicron2D client: Copyright (c) 2019 Matt Young. Available under the BSD 3-Clause license.")
        Omicron2DApp().main(args)
    }
}