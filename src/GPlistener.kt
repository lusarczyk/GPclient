import jpen.*
import jpen.event.PenListener
import java.io.OutputStream

/**
 * Created by QMICSLU on 2018-03-30.
 */

class GPlistener(val outputStream : OutputStream) : PenListener {

    var lastPLevel = 0.0 : Float


    override fun penButtonEvent(e: PButtonEvent) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun penTock(e: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun penLevelEvent(e: PLevelEvent) {


        val p = e.pen.getLevelValue(PLevel.Type.PRESSURE) as Float

        if (p > 0) {
            if (lastPLevel == 0.0) {
                outputStream.write("[start]".toByteArray())
            }
            val x = e.pen.getLevelValue(PLevel.Type.X)
            val y = e.pen.getLevelValue(PLevel.Type.Y)
            outputStream.write("[$x,$y,$p]".toByteArray())
        }
        else if (lastPLevel > 0){
            outputStream.write("[end]".toByteArray())
        }

        lastPLevel = p

    }

    override fun penScrollEvent(e: PScrollEvent) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun penKindEvent(e: PKindEvent) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}