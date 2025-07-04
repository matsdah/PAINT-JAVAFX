package src.backend.model;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import src.backend.Colorable;
import src.backend.Drawable;
import src.backend.Movable;
import java.util.List;

public abstract class Figure implements Movable, Colorable, Drawable{

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

    @Override
    public void setFillColor(Color fillColor){
        this.fillColor = fillColor;
    }

    @Override
    public Color getFillColor(){
        return fillColor;
    }

    @Override
    public void moveTo(double x, double y){
        if(points.length > 0){
            double deltaX = x - points[0].getX();
            double deltaY = y - points[0].getY();
            move(deltaX, deltaY);
        }
    }

    @Override
    public void move(double deltaX, double deltaY){
        for(Point p : points){
            p.move(deltaX, deltaY);
        }
    }

    /*
     * Cada figura debe saber como dividirse a lo ancho y a lo alto, como espejarse
     * horizontalmente y verticalmente, como dibujarse sin contorno y como clonarse.
     */
    public abstract List<Figure> widthDivide(int n);

    public abstract List<Figure> heightDivide(int n);

    public abstract Figure clone();

    public abstract void drawFill(GraphicsContext gc);

    public abstract Figure createHorizontalMirror();

    public abstract Figure createVerticalMirror();
}
