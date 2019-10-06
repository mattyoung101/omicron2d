package com.omicron.simulation2d.tools

import com.omicron.simulation2d.Values
import javafx.application.Application
import javafx.application.Platform
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.image.ImageView
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import javafx.stage.Stage
import mikera.vectorz.Vector2
import org.tinylog.kotlin.Logger
import kotlin.system.exitProcess

private const val VERSION = "0.1"

/**
 * Visual Debugger application by Matt Young (BSD 3-Clause license).
 * This app is used to get an idea of what agents are doing on the field. Eventually, it will support moving them
 * around by way of an offline coach.
 */
class Debugger : Application() {
    override fun start(stage: Stage) {
        stage.title = "Omicron2D Visual Debugger"

        // setup the field, at the top of the file so that we can serialise players in the save dialogue
        val field = Pane()
        val fieldImage = ImageView("field.png")
        val fieldScale = Vector2.of(fieldImage.image.width / Values.FIELD_WIDTH, fieldImage.image.height / Values.FIELD_HEIGHT)
        println("Field scale: ${fieldScale.x} by ${fieldScale.y}")
        field.children.add(fieldImage)

        val fileMenu = Menu("File")
        val close = MenuItem("Close").apply {
            accelerator = KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN)
        }
        close.setOnAction {
            Logger.info("Visual debugger has been closed, exiting app!")
            exitProcess(0)
        }
        fileMenu.items.addAll(close)

        val viewMenu = Menu("View")
        val localisationMenu = Menu("Localisation")
        val showParticles = CheckMenuItem("Show particles")
        val drawLines = CheckMenuItem("Draw lines to landmarks")
        localisationMenu.items.addAll(showParticles, drawLines)
        viewMenu.items.add(localisationMenu)

        val helpMenu = Menu("Help")
        val about = MenuItem("About").apply {
            accelerator = KeyCodeCombination(KeyCode.H, KeyCombination.CONTROL_DOWN)
        }
        about.setOnAction {
            val alert = Alert(
                Alert.AlertType.INFORMATION, "Copyright (c) 2019 Matt Young. BSD 3-Clause license.",
                ButtonType.OK).apply {
                headerText = "Omicron2D Visual Debugger v$VERSION"
                title = "About"
                isResizable = true
                setOnShown {
                    Platform.runLater {
                        isResizable = false
                        dialogPane.scene.window.sizeToScene()
                    }
                }
            }
            alert.show()
        }
        helpMenu.items.add(about)

        val menuBar = MenuBar()
        menuBar.menus.addAll(fileMenu, viewMenu, helpMenu)
        val vbox = VBox(menuBar)
        vbox.isFillWidth = true
        vbox.children.add(field)

        // height is calculated manually due to the fact that you can't just add the height of the fucking MenuBar
        val scene = Scene(vbox, 1254.0, 847.0)
        stage.scene = scene

        stage.show()
    }
}