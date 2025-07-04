package src.backend.model.effects;

import javafx.scene.canvas.GraphicsContext;
import src.backend.model.Figure;
import javafx.scene.paint.Color;

public class LightenEffect implements FigureEffect{
    @Override
    public void apply(GraphicsContext gc, Figure fig){
        gc.save();
        gc.setFill(Color.rgb(255, 255, 255, 0.7));
        fig.drawFill(gc);
        gc.restore();
    }
}
