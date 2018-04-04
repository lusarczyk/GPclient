import com.sun.xml.internal.ws.streaming.XMLStreamWriterUtil.getOutputStream
import jpen.*
import jpen.event.PenListener
import java.io.*
import java.net.HttpURLConnection
import java.net.InetSocketAddress
import java.net.Socket
import java.util.*

/**
 * Created by QMICSLU on 2018-03-30.
 */

class GPlistener(val connection : HttpURLConnection) : PenListener {

    var lastPLevel: Float
    val gpSocket: Socket
    val out: PrintWriter

    init {
        lastPLevel = 0f

        gpSocket = Socket("192.168.1.101",8018)
        out = PrintWriter(gpSocket.getOutputStream(),true)

        gpSocket.keepAlive = true



    }

    override fun penButtonEvent(e: PButtonEvent) {
    }

    override fun penTock(e: Long) {
    }

    override fun penLevelEvent(e: PLevelEvent) {


        val p = e.pen.getLevelValue(PLevel.Type.PRESSURE) as Float


        if (p > 0) {
            if (lastPLevel == 0f) {
                out.print("s")
                out.flush()
            }
            val x = e.pen.getLevelValue(PLevel.Type.X)
            val y = e.pen.getLevelValue(PLevel.Type.Y)

            out.print("[${x.short()},${y.short()},${p.short()}]")
            out.flush()
        }
        else if (lastPLevel > 0f){
            out.print("e")
            out.flush()
        }

        lastPLevel = p
    }

    override fun penScrollEvent(e: PScrollEvent) {
    }

    override fun penKindEvent(e: PKindEvent) {
    }

    fun Float.short() : String {
        return String.format(Locale.ROOT,"%.2f", this)
    }
}