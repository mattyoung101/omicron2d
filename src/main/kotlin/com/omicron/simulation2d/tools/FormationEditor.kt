package com.omicron.simulation2d.tools

import com.esotericsoftware.kryo.Kryo
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.image.ImageView
import javafx.scene.layout.Pane
import javafx.scene.layout.Region
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.stage.FileChooser
import javafx.stage.Stage
import mikera.vectorz.Vector2
import kotlin.system.exitProcess
import javafx.scene.text.TextBoundsType
import javafx.scene.shape.Circle
import javafx.scene.text.Text
import java.util.concurrent.ThreadLocalRandom


private const val VERSION = "0.1a"

class FormationEditor : Application() {
    private val kryo = Kryo().apply {
        register(Vector2::class.java)
        register(List::class.java)
    }

    override fun start(stage: Stage) {
        stage.title = "Omicron2D Formation Editor"

        val fileChooser = FileChooser()
        fileChooser.extensionFilters.add(
            FileChooser.ExtensionFilter("Formation files (*.formation)", "formation"))

        val fileMenu = Menu("File")
        val openFormation = MenuItem("Open formation")
        openFormation.setOnAction {
            fileChooser.title = "Open formation file"
            val selectedFile = fileChooser.showOpenDialog(stage)

            if (selectedFile != null){
                // TODO deserialise and display it here
            }
        }

        val saveFormation = MenuItem("Save formation")
        saveFormation.setOnAction {
            fileChooser.title = "Save formation file"
            val selectedFile = fileChooser.showSaveDialog(stage)

            if (selectedFile != null){
                // TODO serialise and write to disk
                Alert(Alert.AlertType.INFORMATION, "Saved to $selectedFile", ButtonType.OK).apply {
                    headerText = "Written to disk successfully."
                    // ridiculous hack to make it display more text: https://stackoverflow.com/a/33905734/5007892
                    dialogPane.children.stream().filter { node -> node is Label }.forEach { node ->
                        (node as Label).minHeight =
                            Region.USE_PREF_SIZE
                    }
                }.showAndWait()
            }
        }

        val close = MenuItem("Close")
        close.setOnAction {
            println("Exiting Formation Editor...")
            exitProcess(0)
        }
        fileMenu.items.addAll(openFormation, saveFormation, close)

        val helpMenu = Menu("Help")
        val about = MenuItem("About")
        about.setOnAction {
            Alert(Alert.AlertType.INFORMATION, "Copyright (c) 2019 Matt Young. BSD 3-Clause license.",
                ButtonType.OK).apply {
                headerText = "Omicron2D Formation Editor v$VERSION"
                title = "About"
            }.showAndWait()
        }
        helpMenu.items.add(about)

        val menuBar = MenuBar()
        menuBar.menus.addAll(fileMenu, helpMenu)
        val vbox = VBox(menuBar)
        vbox.isFillWidth = true

        val field = Pane()
        val fieldImage = ImageView("field.png")
        field.children.add(fieldImage)

        // create circles (players)
        for (i in 0..10){
            var orgSceneX = 0.0
            var orgSceneY = 0.0

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
                layoutX = ThreadLocalRandom.current().nextDouble(0.0, fieldImage.image.width / 2)
                layoutY = ThreadLocalRandom.current().nextDouble(0.0, fieldImage.image.height)

                // Source: http://www.java2s.com/Tutorials/Java/JavaFX_How_to/Shape/Handle_Shape_drag_and_drop_events.htm
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
        }
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