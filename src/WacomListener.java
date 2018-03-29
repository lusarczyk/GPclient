import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import jpen.*;
import jpen.event.PenAdapter;
import jpen.event.PenListener;

public class WacomListener extends PenAdapter {

    private final GraphicsContext graphicsContext;

    public WacomListener(GraphicsContext graphicsContext) {
        this.graphicsContext=graphicsContext;
    }

    @Override
    public void penKindEvent(PKindEvent pKindEvent) {
        System.out.println("pKindEvent = " + pKindEvent);
    }

    @Override
    public void penLevelEvent(PLevelEvent e) {

        float p = e.pen.getLevelValue(PLevel.Type.PRESSURE);
        if (p > 0) {
            System.out.println(e.pen.getLevelValue(PLevel.Type.X)+" : "+e.pen.getLevelValue(PLevel.Type.Y));
            graphicsContext.fillOval(e.pen.getLevelValue(PLevel.Type.X), e.pen.getLevelValue(PLevel.Type.Y),p*10,p*10);
        }
    }

    @Override
    public void penButtonEvent(PButtonEvent pButtonEvent) {

        //System.out.println("pButtonEvent = " + pButtonEvent);
    }

    @Override
    public void penScrollEvent(PScrollEvent pScrollEvent) {
    }

    @Override
    public void penTock(long l) {

    }
}
