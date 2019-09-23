package com.omicron.simulation2d

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.int
import org.pmw.tinylog.Configurator
import org.pmw.tinylog.Level
import org.pmw.tinylog.Logger
import org.pmw.tinylog.labelers.CountLabeler
import org.pmw.tinylog.policies.StartupPolicy
import org.pmw.tinylog.writers.ConsoleWriter
import org.pmw.tinylog.writers.RollingFileWriter
import java.io.File
import java.util.*
import kotlin.concurrent.thread
import kotlin.system.exitProcess

class Omicron2DApp : CliktCommand(){
    private val numberClients by option(help="Number of clients to connect").int().default(11)
    private val startServer by option(help="Start an instance of rcssserver").flag()
    private val startMonitor by option(help="Start an instance of soccerwindow2").flag()
    private val startOpposition by option(help="Starts a team of simple clients to play against").flag()

    override fun run() {
        var server: Process? = null
        var monitor: Process? = null
        val opposition: Process? = null

        // start server if requested
        if (startServer){
            Logger.info("Starting rcssserver...")
            server = ProcessBuilder()
                .directory(File("../../../logs/"))
                .command("rcssserver")
                .inheritIO()
                .start()
            Thread.sleep(1500) // wait for it to start
        }

        // run our team
        val team = Omicron2DTeam()
        if (numberClients == 11){
            Logger.trace("Running default team of 11 players")
            team.connectAll()
        } else {
            Logger.trace("Running shortened team of $numberClients players")
            for (i in 0 until numberClients){
                team.connect(i)
            }
        }
        // TODO start 11 agent2d's (or another team's binary) here

        if (startMonitor){
            Logger.info("Starting monitor...")
            // currently we use rcssmonitor instead of soccerwindow2 because it's faster to start up
            monitor = ProcessBuilder().command("rcssmonitor").inheritIO().start()
            monitor?.onExit()?.thenAccept {
                // if the user closes the monitor, quit the app as well
                Logger.info("Monitor has terminated! Shutting down...")
                exitProcess(0)
            }
        }

        // clean up any sub-processes we may have created
        if (server != null || monitor != null || opposition != null) {
            Runtime.getRuntime().addShutdownHook(thread(start = false) {
                println("Shutting down sub-processes...")
                server?.destroy()
                monitor?.destroy()
            })
        }
    }
}

object Main {
    @JvmStatic
    fun main(args: Array<String>){
        Configurator.currentConfig().removeAllWriters()
            .locale(Locale.ENGLISH)
            .level(Level.TRACE)
            .formatPattern("{date} {level} [{class_name}:{line}] {message}")
            .writer(
                RollingFileWriter(
                System.getProperty("user.home") + "/Documents/TeamOmicron/Omicron2D/omicron2d.log",
                4, CountLabeler(), StartupPolicy()
                )
            )
            .writingThread(false)
            .addWriter(ConsoleWriter())
            .activate()
        System.setProperty("log4j.configuration", "log4j.properties")

        Logger.info("Omicron2D client: Copyright (c) 2019 Matt Young. Available under the BSD 3-Clause license.")
        Omicron2DApp().main(args)
    }
}