package com.omicron.simulation2d

import com.omicron.simulation2d.server.ServerConnection
import org.pmw.tinylog.Configurator
import org.pmw.tinylog.Level
import org.pmw.tinylog.Logger
import org.pmw.tinylog.labelers.CountLabeler
import org.pmw.tinylog.policies.StartupPolicy
import org.pmw.tinylog.writers.ConsoleWriter
import org.pmw.tinylog.writers.RollingFileWriter
import java.util.*

object Main {
    @JvmStatic
    fun main(args: Array<String>){
        Configurator.currentConfig().removeAllWriters()
            .locale(Locale.ENGLISH)
            .level(Level.DEBUG)
            .formatPattern("{date} {level} [{class_name}:{line}] {message} ")
            .writer(
                RollingFileWriter(
                System.getProperty("user.home") + "/Documents/TeamOmicron/Omicron2D/omicron2d.log",
                4, CountLabeler(), StartupPolicy()
                )
            )
            .writingThread(false)
            .addWriter(ConsoleWriter())
            .activate()

        Logger.info("Omicron2D client - Copyright (c) 2019 Matt Young, BSD 3-Clause license.")
        Logger.trace("Connecting to rcssserver...")
        val connection = ServerConnection()
        connection.connect()
    }
}