package io.github.omicron2d

import com.esotericsoftware.yamlbeans.YamlReader
import io.github.omicron2d.communication.PlayerAgent
import io.github.omicron2d.communication.messages.OutgoingInitMessage
import io.github.omicron2d.utils.GeneralConfig
import io.github.omicron2d.utils.SERVER_PROTOCOL_VERSION
import io.github.omicron2d.utils.VERSION
import org.tinylog.kotlin.Logger
import java.io.FileReader
import java.net.InetAddress

/*
 * Notes:
 * I think the best way to arrange the Main is to have it do what it's supposed to, start a single agent.
 * Then make a file called like TestRunner.kt which runs the agents and tools and everything separately.
 * We would also have to write a shell script to launch the agent using an embedded JVM for at the venue.
 * Maybe use java packager with the jvm for that to reduce build size. We can worry about that if we get to it.
 */

object Main {
    @JvmStatic
    fun main(args: Array<String>){
        System.setProperty("log4j.configuration", "log4j.properties")
        System.setProperty("tinylog.configuration", "tinylog.properties")
        System.setProperty("kryo.unsafe", "false")

        Logger.info("Omicron2D v$VERSION: Copyright (c) 2019-2020 Matt Young.")

        // load config from YAML files
        val yamlReader = YamlReader(FileReader("config_general.yml"))
        val generalConfig = yamlReader.read(GeneralConfig::class.java)
        Logger.debug("General config parsed successfully")

        Logger.debug("Connecting to ${generalConfig.serverHost}:${generalConfig.playerPort}")
        val initMessage = OutgoingInitMessage(generalConfig.teamName,
            SERVER_PROTOCOL_VERSION, false)

        val agent = PlayerAgent(InetAddress.getByName(generalConfig.serverHost), generalConfig.playerPort)
        agent.connect(initMessage)
        agent.await()
        agent.disconnect()
    }
}