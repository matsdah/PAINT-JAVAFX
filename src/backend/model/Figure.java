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

    protected Figure(Point[] points, Color fillColor, Border border){
        this.points = points;
        this.fillColor = fillColor;
        this.border = border;
    }

    public abstract boolean contains(Point point);

    /* Getters y setters */
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
        if (points.length > 0) {
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