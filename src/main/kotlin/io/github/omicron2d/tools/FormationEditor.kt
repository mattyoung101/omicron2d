package io.github.omicron2d.tools

import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.io.Output
import io.github.omicron2d.Values.FIELD_CENTRE
import io.github.omicron2d.utils.FIELD_WIDTH
import io.github.omicron2d.utils.FIELD_HEIGHT
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


private const val VERSION = "1.0"

/**
 * Formation Editor applicatio.
 * Not the cleanest code ever, pretty much just hacked together to be usable in a few days.
 * You can use this app to position the field into a formation and save it to a Kryo serialised file.
 * For normal use, only use the left hand side of the field because the agent will automatically mirror the formation
 * if it starts on the west side.
 * Note that because the image is a screencap from rcssmonitor (not an actual render), positions aren't 100% accurate.
 *
 * TODO:
 *  - add support for opening formations
 *  - add undo key
 *  - prompt on exit if unsaved changes
 */
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
            // TODO add open functionality (will require deserialising and fixing positions)

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
                    val pos = Vector2.of(it.layoutX, it.layoutY).apply {
                        divide(fieldScale)
                        sub(FIELD_CENTRE)
                    }
                    println("Position: (${it.layoutX}, ${it.layoutY}), scaled: (${pos.x}, ${pos.y})")
                    pos
                }.toTypedArray()

                // serialise to disk
                kryo.writeObject(output, positions)
                output.close()
                println("Written to disk successfully.")
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
            val alert = Alert(Alert.AlertType.INFORMATION, "Copyright (c) 2019-2020 Matt Young. MPL 2.0 licence.",
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

        // print position of goalie to console for debugging unit conversions
        scene.setOnKeyPressed {
            if (it.code == KeyCode.SPACE){
                val goalie = players[0]
                val pos = Vector2.of(goalie.layoutX, goalie.layoutY).apply {
                    divide(fieldScale)
                    sub(FIELD_CENTRE)
                }
                println("Goalie pos: (${pos.x}, ${pos.y})")
            }
        }

        stage.show()
    }
}

fun main(args: Array<String>){
    println("Launching Formation Editor...")
    System.setProperty("kryo.unsafe", "false")
    Application.launch(FormationEditor::class.java, *args)
}