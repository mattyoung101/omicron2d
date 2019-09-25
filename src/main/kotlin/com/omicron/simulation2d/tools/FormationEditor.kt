package com.omicron.simulation2d.tools

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.layout.VBox
import javafx.stage.Stage
import kotlin.system.exitProcess

private const val VERSION = "0.1a"

class FormationEditor : Application() {
    override fun start(stage: Stage) {
        stage.title = "Omicron2D Formation Editor"

        val fileMenu = Menu("File")
        val newFormation = MenuItem("New formation")
        val openFormation = MenuItem("Open formation")
        val close = MenuItem("Close")
        close.setOnAction {
            println("Exiting Formation Editor...")
            exitProcess(0)
        }
        fileMenu.items.addAll(newFormation, openFormation, close)

        val helpMenu = Menu("Help")
        val about = MenuItem("About")
        about.setOnAction {
            Alert(Alert.AlertType.INFORMATION,
                "Application for editing formations in RoboCup 2D soccer." +
                        "\nCopyright (c) 2019 Matt Young, BSD 3-Clause license.",
                ButtonType.OK).apply {
                headerText = "Omicron2D Formation Editor v$VERSION"
            }.showAndWait()
        }
        helpMenu.items.add(about)

        val menuBar = MenuBar()
        menuBar.menus.addAll(fileMenu, helpMenu)

        val vbox = VBox(menuBar)
        val scene = Scene(vbox, 1600.0, 900.0)
        stage.scene = scene
        stage.show()
    }
}

fun main(args: Array<String>){
    println("Launching Formation Editor...")
    Application.launch(FormationEditor::class.java, *args)
}