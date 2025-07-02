package src.backend;

import javafx.scene.canvas.GraphicsContext;
import src.backend.model.*;

public class CanvasFigure implements Movable, Drawable{

    public CanvasFigure(Figure fig){
        this.fig = fig;
    }

    private final Figure fig;
    private Figure lightenedFig;
    private Figure darkenedFig;
    private Figure vMirroredFig;
    private Figure hMirroredFig;

    /*
     * Mueve la figura junto a todos los efectos. Cada figura debe saber moverse.
     */
    public void move(int x, int y){
        fig.move(x, y);
        lightenedFig.move(x, y);
        darkenedFig.move(x, y);
        vMirroredFig.move(x, y);
        hMirroredFig.move(x, y);
    }

    public boolean contains(Point point){
        return fig.contains(point);
    }

    public void setOffLightened(){

    }

    public void setOnLightened(){

    }

    @Override
    public void move(double deltaX, double deltaY) {
        lightenedFig.move(deltaX, deltaY);
        darkenedFig.move(deltaX, deltaY);
        vMirroredFig.move(deltaX, deltaY);
        hMirroredFig.move(deltaX, deltaY);
    }

    @Override
    public void draw(GraphicsContext gc){
        fig.draw(gc);
    }
}