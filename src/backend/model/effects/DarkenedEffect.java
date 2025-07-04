package src.backend.model.effects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import src.backend.model.Figure;

public class DarkenedEffect implements FigureEffect{
    @Override
    public void apply(GraphicsContext gc, Figure fig){
        gc.save();
        gc.setFill(Color.rgb(0, 0, 0, 0.3));
        fig.drawFill(gc);
        gc.restore();
    }
}
