package com.omicron.simulation2d.tools

import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.io.Output
import com.omicron.simulation2d.Values.FIELD_WIDTH
import com.omicron.simulation2d.Values.FIELD_HEIGHT
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
import javafx.scene.paint.Color
import javafx.stage.FileChooser
import javafx.stage.Stage
import mikera.vectorz.Vector2
import kotlin.system.exitProcess
import javafx.scene.text.TextBoundsType
import javafx.scene.shape.Circle
import javafx.scene.text.Text
import java.io.FileOutputStream
import java.nio.file.Paths

/*
 * Formation Editor application by Matt Young (BSD 3-Clause license).
 * Not the cleanest code ever, pretty much just hacked together to be usable in a few days.
 * You can use this app to position the field into a formation and save it to a Kryo serialised file.
 * For normal use, only use the left hand side of the field because the agent will automatically mirror the formation
 * if it starts on the west side.
 * Note that because the image is a screencap from rcssmonitor (not an actual render), positions aren't 100% accurate.
 */

private const val VERSION = "0.1a"

class FormationEditor : Application() {
    private val kryo = Kryo().apply {
        register(Array<Vector2>::class.java)
        register(Vector2::class.java)
    }

    override fun start(stage: Stage) {
        stage.title = "Omicron2D Formation Editor"

        // setup the field, at the top of the file so that we can serialise players in the save dialogue
        val field = Pane()
        val fieldImage = ImageView("field.png")
        val fieldScale = Vector2.of(fieldImage.image.width / FIELD_WIDTH, fieldImage.image.height / FIELD_HEIGHT)
        println("Field scale: ${fieldScale.x} by ${fieldScale.y}")
        field.children.add(fieldImage)
        val players = mutableListOf<Pane>()

        // create circles (players)
        for (i in 0..10){
            val circle = Circle().apply {
                radius = 16.0
                fill = if (i == 0) Color.CYAN else Color.YELLOW
                stroke = Color.BLACK
            }
            val text = Text(i.toString()).apply {
                boundsType = TextBoundsType.VISUAL
                fill = Color.BLACK
                layoutXProperty().bind(circle.layoutXProperty())
                layoutYProperty().bind(circle.layoutYProperty())
            }
            text.boundsType = TextBoundsType.VISUAL

            val player = Pane().apply {
                layoutX = 64.0 + (i * 36.0)
                layoutY = 128.0

                // Source: http://www.java2s.com/Tutorials/Java/JavaFX_How_to/Shape/Handle_Shape_drag_and_drop_events.htm
                var orgSceneX = 0.0
                var orgSceneY = 0.0
                setOnMousePressed {
                    orgSceneX = it.sceneX
                    orgSceneY = it.sceneY
                }
                setOnMouseDragged {
                    val offsetX = it.sceneX - orgSceneX
                    val offsetY = it.sceneY - orgSceneY

                    this.layoutX = layoutX + offsetX
                    this.layoutY = layoutY + offsetY

                    orgSceneX = it.sceneX
                    orgSceneY = it.sceneY
                }
            }
            player.children.addAll(circle, text)
            field.children.add(player)
            players.add(player)
        }

        val fileChooser = FileChooser().apply {
            initialDirectory = Paths.get("src/main/resources").toFile()
        }
        fileChooser.extensionFilters.add(FileChooser.ExtensionFilter(
            "Formation files (*.formation)", "*.formation"))

        val fileMenu = Menu("File")
        val openFormation = MenuItem("Open formation").apply {
            accelerator = KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN)
        }
        openFormation.setOnAction {
            // FIXME add open functionality (will recquire deserialising and fixing positions)
//            fileChooser.title = "Open formation file"
//            val selectedFile = fileChooser.showOpenDialog(stage)

//            if (selectedFile != null){
                val alert = Alert(Alert.AlertType.ERROR, "This will be added in the future.",
                    ButtonType.OK).apply {
                    headerText = "Open functionality has not yet been implemented."

                    // I can't believe how fucking broken these alert boxes are.
                    // Fix from: https://github.com/javafxports/openjdk-jfx/issues/222#issuecomment-458690238
                    isResizable = true
                    setOnShown {
                        Platform.runLater {
                            isResizable = false
                            dialogPane.scene.window.sizeToScene()
                        }
                    }
                }
                alert.show()
//            }
        }

        val saveFormation = MenuItem("Save formation").apply {
            accelerator = KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN)
        }
        saveFormation.setOnAction {
            fileChooser.title = "Save formation file"
            val selectedFile = fileChooser.showSaveDialog(stage)

            if (selectedFile != null){
                println("Writing to file: $selectedFile")
                selectedFile.createNewFile()
                val output = Output(FileOutputStream(selectedFile)).apply { variableLengthEncoding = true }

                // collect positions
                val positions = players.map {
                    val pos = Vector2.of(it.layoutX, it.layoutY).apply { divide(fieldScale) }
                    println("Position: (${it.layoutX}, ${it.layoutY}), scaled: (${pos.x}, ${pos.y})")
                    // TODO then I think we need to subtract the centre?
                    pos
                }.toTypedArray()

                // serialise to disk
                kryo.writeObject(output, positions)
                output.close()
                println("Operation completed.")
            }
        }

        val close = MenuItem("Close").apply {
            accelerator = KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN)
        }
        close.setOnAction {
            println("Exiting Formation Editor...")
            exitProcess(0)
        }
        fileMenu.items.addAll(openFormation, saveFormation, close)

        val helpMenu = Menu("Help")
        val about = MenuItem("About").apply {
            accelerator = KeyCodeCombination(KeyCode.H, KeyCombination.CONTROL_DOWN)
        }
        about.setOnAction {
            val alert = Alert(Alert.AlertType.INFORMATION, "Copyright (c) 2019 Matt Young. BSD 3-Clause license.",
                ButtonType.OK).apply {
                headerText = "Omicron2D Formation Editor v$VERSION"
                title = "About"

                // I can't believe how fucking broken these alert boxes are.
                // Fix from: https://github.com/javafxports/openjdk-jfx/issues/222#issuecomment-458690238
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
        menuBar.menus.addAll(fileMenu, helpMenu)
        val vbox = VBox(menuBar)
        vbox.isFillWidth = true
        vbox.children.add(field)

        // height is calculated manually due to the fact that you can't just add the height of the fucking MenuBar
        val scene = Scene(vbox, 1254.0, 847.0)
        stage.scene = scene
        stage.show()
    }
}

fun main(args: Array<String>){
    println("Launching Formation Editor...")
    System.setProperty("kryo.unsafe", "false")
    Application.launch(FormationEditor::class.java, *args)
}