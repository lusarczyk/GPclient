import javafx.stage.Stage

class Controller(val stage : Stage?){

    fun toggleFullscreen() {

        if (!stage?.isFullScreen()!!) {
            stage.isFullScreen = true
        }

    }

}