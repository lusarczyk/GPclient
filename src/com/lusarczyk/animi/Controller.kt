package com.lusarczyk.animi

import javafx.stage.Stage
import java.net.HttpURLConnection
import java.net.Socket
import java.net.URL

class Controller(val stage : Stage?){
    var ipAddress = ""
    var port = ""
    var socket : Socket? = null

    fun toggleFullscreen() {

        if (!stage?.isFullScreen()!!) {
            stage.isFullScreen = true
        }

    }


    fun connect() {
        println("prt::: $port")
        socket = Socket(ipAddress,Integer.parseInt(port))
        val s = socket
        print("connecting socket")
        if (s != null) {
            for (i in 1..10) {
                print(".")
                if (s.isConnected) {
                    print("\nConnected!\n")
                    return
                }
                Thread.sleep(1000)
            }

        }
        else {
            error("socket = null")
        }
        error("Can't establish connection")
    }

}