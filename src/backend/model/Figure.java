package src.backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import src.backend.Colorable;
import src.backend.Drawable;
import src.backend.Movable;

public abstract class Figure implements Movable, Colorable, Drawable{

    private static final Color borderColor = Color.rgb(0, 0, 0, 1);
    private static final Color selectedBorder = Color.rgb(255, 0, 0, 1);

    private Color fillColor;                /* Color del relleno de la figura */
    private Border border;
    private final Point[] points;           /* Determinan la posicion de la figura en el lienzo */

    protected Figure(Point[] points){
        this.points = points;
    }

    /*
     * Metodo que permite verificar en el lienzo si el puntero cae sobre una figura o no.
     */
    public abstract boolean contains(Point point);

    /*public void setBorderSize(double borderSize){
        if(borderSize <= 0) {
            throw new IllegalArgumentException("El tamaño del borde debe ser positivo.");
        }
        this.borderSize = borderSize;
    }*/

    /*public double getBorderSize(){
        return borderSize;
    }*/

    @Override
    public void setFillColor(Color fillColor){
        this.fillColor = fillColor;
    }

    @Override
    public Color getFillColor(){
        return fillColor;
    }

    /*public void setBorder(StrokeLineCap newB){
        this.border = newB;
    }*/

    @Override
    public void move(double deltaX, double deltaY){
        for(Point p : points){
            p.move(deltaX, deltaY);
        }
    }

    /*@Override
    public boolean equals(Object o){
        return o instanceof Figure f && // comparar borde, color y puntos
    }*/
}