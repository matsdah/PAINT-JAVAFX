package src.backend.model.effects;

import javafx.scene.canvas.GraphicsContext;
import src.backend.model.Figure;

public interface FigureEffect {
    public void apply(GraphicsContext gc, Figure fig);
}
