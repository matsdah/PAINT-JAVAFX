package src.backend;

import javafx.scene.canvas.GraphicsContext;

@FunctionalInterface
public interface Drawable{
    void draw(GraphicsContext gc, boolean isSelected);
}