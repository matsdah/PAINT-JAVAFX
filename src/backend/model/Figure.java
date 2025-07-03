package src.backend.model;
import javafx.scene.paint.Color;
import src.backend.Colorable;
import src.backend.Drawable;
import src.backend.Movable;
import java.util.List;

public abstract class Figure implements Movable, Colorable, Drawable {

    protected Color fillColor;                /* Color del relleno de la figura */
    protected Border border;                  /* Estilo de borde de la figura */
    protected final Point[] points;           /* Determinan la posicion de la figura en el lienzo */

    protected Figure(Point[] points, Color fillColor, Border border){
        this.points = points;
        this.fillColor = fillColor;
        this.border = border;
    }

    /*
     * Metodo que permite verificar en el lienzo si el puntero cae sobre una figura o no.
     */
    public abstract boolean contains(Point point);

    public Border getBorder(){
        return border;
    }

    public void setBorder(Border border){
        this.border = border;
    }

    public abstract Figure multiply();
/*
    public void translate(Point newPosition) {
        this.points[0] = newPosition;
    }*/

    @Override
    public void setFillColor(Color fillColor){
        this.fillColor = fillColor;
    }

    @Override
    public Color getFillColor(){
        return fillColor;
    }

    @Override
    public void move(double deltaX, double deltaY){
        for(Point p : points){
            p.move(deltaX, deltaY);
        }
    }

    public List<Figure> widthDivide(int n){
        Point aux = new Point(1.0 / n, 1);
        return division(n, aux);
    }

    public List<Figure> heightDivide(int n){
        Point aux = new Point(1, 1.0 / n);
        return division(n, aux);
    }

    protected abstract List<Figure> division(int n, Point dir);

}
