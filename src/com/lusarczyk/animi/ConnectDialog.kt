package com.lusarczyk.animi

import com.lusarczyk.animi.Controller
import javafx.geometry.Insets
import javafx.scene.control.*
import javafx.scene.layout.HBox
import tornadofx.action
import tornadofx.add
import tornadofx.button
import tornadofx.text

class ConnectDialog(val controller: Controller) {

    val dialog = Dialog<String>()

    val defaultPort = "8018"
//    val defaultIp = "192.168.1.101"
    val defaultIp = "100.93.10.62"

    init {
        dialog.title = "Connect"
        val ipTF = TextField(defaultIp)
        controller.ipAddress = defaultIp
        controller.port = defaultPort
        ipTF.apply {
                        action { controller.ipAddress = text }
                   }
        val portTF = TextField(defaultPort)
        portTF.apply {
                        action { controller.port = text }
                     }

        val tfBox = HBox()
        tfBox.spacing = 10.0
        tfBox.add(Label("IP"))
        tfBox.add(ipTF)
        tfBox.add(Label("Port"))
        tfBox.add(portTF)

        val connectButton = ButtonType("Connect")
        connectButton.run { println("Connecting") }

        dialog.dialogPane.content = tfBox
        dialog.dialogPane.buttonTypes.add(connectButton)

    }

    fun show() {
        dialog.showAndWait()
        controller.connect()

    }



}