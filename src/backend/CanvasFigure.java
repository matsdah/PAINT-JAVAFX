package src.backend;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import src.backend.model.*;

public class CanvasFigure implements Movable, Drawable{
    private final Figure fig;           /* Figura principal */

    /* Efectos de la figura */
    private Figure lightenedFig;
    private Figure darkenedFig;
    private Figure vMirroredFig;
    private Figure hMirroredFig;

    public CanvasFigure(Figure fig){
        this.fig = fig;
    }

    /*
     * Mueve la figura junto a todos los efectos. Cada figura debe saber moverse.
     */
    @Override
    public void move(double deltaX, double deltaY){
        fig.move(deltaX, deltaY);
        lightenedFig.move(deltaX, deltaY);
        darkenedFig.move(deltaX, deltaY);
        vMirroredFig.move(deltaX, deltaY);
        hMirroredFig.move(deltaX, deltaY);
    }

    public void multiply(){

    }
/*
    public void transfer(int x, int y){
        fig.transfer(x, y);
        lightenedFig.transfer(x, y);
        darkenedFig.transfer(x, y);
        vMirroredFig.transfer(x, y);
        hMirroredFig.transfer(x, y);
    }*/

    public boolean contains(Point point){
        return fig.contains(point);
    }

    public void setFillColor(Color color){
        fig.setFillColor(color);
    }

    public void setBorder(Border border){
        fig.setBorder(border);
    }

    public Color getFillColor(){
        return fig.getFillColor();
    }

    public Border getBorder(){
        return fig.getBorder();
    }

    public CanvasFigure copy(){
        return new CanvasFigure(fig);
    }

    @Override
    public void draw(GraphicsContext gc, boolean isSelected){
        fig.draw(gc, isSelected);
    }

    @Override
    public String toString(){
        return fig.toString();
    }
}