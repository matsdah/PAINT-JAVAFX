package src.backend.model;
import src.backend.Colorable;
import src.backend.Drawable;
import src.backend.Movable;
import src.backend.DrawingContext;
import java.util.List;

public abstract class Figure implements Movable, Colorable, Drawable{

    protected Color fillColor;
    protected Border border;
    protected final Point[] points;

    /**
     * Crea una figura a partir de sus puntos, color de relleno y tipo de borde.
     *
     * @param points        Arreglo de puntos que definen la figura espacialmente en el lienzo.
     * @param fillColor     Color de relleno de la figura.
     * @param border        Tipo de borde de la figura.
     */
    protected Figure(Point[] points, Color fillColor, Border border){
        this.points = points;
        this.fillColor = fillColor;
        this.border = border;
    }

    /**
     * Verifica si un punto en el lienzo está comprendido dentro de la figura actual.
     *
     * @param point     Punto a verificar.
     * @return          true si el punto está dentro de la figura; false sino.
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
        for (Point p : points) {
            p.move(deltaX, deltaY);
        }
    }

    public abstract List<Figure> widthDivide(int n);

    public abstract List<Figure> heightDivide(int n);

    public abstract Figure clone();

    public abstract void drawFill(DrawingContext dc);

    public abstract Figure createHorizontalMirror();

    public abstract Figure createVerticalMirror();
}