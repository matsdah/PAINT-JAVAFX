package src.backend.model;
import javafx.scene.paint.Color;
import src.backend.Colorable;
import src.backend.Drawable;
import src.backend.Movable;
import java.util.List;
import java.util.Objects;

public abstract class Figure implements Movable, Colorable, Drawable {

/*
    private static final Color borderColor = Color.BLACK;
    private static final Color selectedBorder = Color.RED;
*/

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

    public void transfer(int x, int y){

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
    public void move(double deltaX, double deltaY){
        for(Point p : points){
            p.move(deltaX, deltaY);
        }
    }

    @Override
    public boolean equals(Object o){
        return o instanceof Figure f && (Objects.equals(fillColor, f.getFillColor())) && (border == f.getBorder());
    }

    @Override
    public int hashCode(){
        return Objects.hash(fillColor, border);
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
