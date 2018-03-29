import javafx.scene.canvas.Canvas
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import jpen.PLevel
import jpen.Pen
import jpen.PenManager
import jpen.PenProvider
import jpen.event.PenAdapter
import jpen.event.PenListener
import jpen.event.PenManagerListener
import jpen.owner.ScreenPenOwner
import jpen.provider.wintab.WintabProvider
import tornadofx.View
import java.net.HttpURLConnection
import java.net.URL
import com.sun.xml.internal.ws.streaming.XMLStreamWriterUtil.getOutputStream
import java.io.DataOutputStream
import java.io.OutputStream


/**
 * Created by michał on 2018-03-26.
 */

class AnimiView : View() {

    val connection = URL("http://192.168.1.101:8018").openConnection() as HttpURLConnection




    override val root = VBox()
    val canvas = Canvas(1000.0,800.0);
    val gc = canvas.graphicsContext2D;

    val po = WacomOwner(canvas)
    val pm = PenManager(po)
    val penListener = WacomListener(gc)

    init {

        try {
//            val data = connection.inputStream.bufferedReader().readText()
//            print(data)

            connection.setRequestMethod("POST")
            connection.setDoOutput(true)
            connection.connect()
            val os = connection.getOutputStream()
            os.write("Scottshak".toByteArray())
            println("resp: "+connection.responseMessage)

        } finally {
            connection.disconnect()
        }


        pm.pen.addListener(penListener)
        root.children.add(canvas)
    }

}