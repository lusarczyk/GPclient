package com.lusarczyk.animi

import javafx.scene.canvas.Canvas
import javafx.scene.control.Button
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.stage.Screen
import jpen.PenManager
import tornadofx.View
import tornadofx.onChange
import java.util.*
import kotlin.system.exitProcess


/**
 * Created by michał on 2018-03-26.
 */

class AnimiView : View() {

    override val root = VBox()

    init {

        val controller = Controller(currentStage)
        val cd = ConnectDialog(controller)
        cd.show()

        val buttons = HBox()
        val tools = Tools(controller)

        buttons.children.addAll(tools.buttons)
        root.children.add(buttons)
        val screenWidth = Screen.getPrimary().bounds.width
        val screenHeight = Screen.getPrimary().bounds.height
        val canvas = Canvas(screenWidth, screenHeight);
        val gc = canvas.graphicsContext2D;

        val socket = controller.socket
        if (socket != null) {
            val po = WacomOwner(canvas)
            val pm = PenManager(po)
            val penListener = WacomListener(gc)
            val gpListener = GPlistener(socket)
            pm.pen.addListener(penListener)
            pm.pen.addListener(gpListener)
        }
        else {
            exitProcess(1)
        }
        currentStage?.widthProperty()?.onChange {println(it/screenWidth); canvas.scaleX = it/screenWidth }
        currentStage?.heightProperty()?.onChange { canvas.scaleY = it/screenHeight }

        root.children.add(canvas)
        root.setPrefSize(1000.0, 800.0)
        canvas.widthProperty().bind(root.widthProperty())
        canvas.heightProperty().bind(root.heightProperty())
        currentStage?.xProperty()?.onChange { println(it) }
    }


}

class Tools(val controller: Controller) {

    val buttons : MutableList<Button> = LinkedList()


    init {
        val buttFS = Button("fullscreen")
        buttFS.setOnKeyPressed { if (it.code.getName().equals("F")) controller.toggleFullscreen() }
        buttFS.setOnAction { controller.toggleFullscreen() }
        buttons.add(buttFS)
    }

}
