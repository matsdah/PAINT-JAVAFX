package src.backend.model.effects;

import javafx.scene.canvas.GraphicsContext;
import src.backend.model.Figure;

public class HorizontalMirrorEffect implements FigureEffect{
    @Override
    public void apply(GraphicsContext gc, Figure fig){
        fig.createHorizontalMirror().draw(gc, false);
    }
}
