package com.omicron.simulation2d

import java.awt.Dimension
import java.awt.event.ActionEvent
import javax.swing.*

object FormationEditor {
    // TODO this should really be using javafx if I could fucking get it to rock up in IntelliJ
    private fun createAndShowGUI() {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())

        val frame = JFrame("Omicron2D Formation Editor")
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE

        val menuBar = JMenuBar()
        val fileMenu = JMenu("File")
        val helpMenu = JMenu("Help")

        val aboutOption = JMenuItem("About")
//        aboutOption.action = object : AbstractAction() {
//            override fun actionPerformed(e: ActionEvent?) {
//                println("Cool")
//            }
//        }
        helpMenu.add(aboutOption)

        val newOption = JMenuItem("New formation")
        val openOption = JMenuItem("Open existing formation")
        val exitOption = JMenuItem("Exit editor")
        fileMenu.apply {
            add(newOption)
            add(openOption)
            add(exitOption)
        }

        menuBar.add(fileMenu)
        menuBar.add(helpMenu)
        frame.jMenuBar = menuBar

        frame.pack()
        frame.setSize(1600, 900)
        frame.isVisible = true
        frame.setLocationRelativeTo(null)
    }

    @JvmStatic
    fun main(args: Array<String>){
        println("Running formation editor...")
        SwingUtilities.invokeLater { createAndShowGUI() }
    }
}