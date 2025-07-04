package src.backend.model;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class Rectangle extends Figure{

    private final Point topLeft, bottomRight;

    public Rectangle(Point corner1, Point corner2, Color fillColor, Border border){
        Point auxTL = new Point(Math.min(corner1.getX(), corner2.getX()), Math.min(corner1.getY(), corner2.getY()));
        Point auxBR = new Point(Math.max(corner1.getX(), corner2.getX()), Math.max(corner1.getY(), corner2.getY()));
        super(new Point[]{auxTL, auxBR}, fillColor, border);
        this.topLeft = auxTL;
        this.bottomRight = auxBR;
    }

    @Override
    public void drawFill(GraphicsContext gc){
        gc.fillRect(topLeft.getX(), topLeft.getY(), getWidth(), getHeight());
    }

    @Override
    public Figure createHorizontalMirror(){
        Point newTopLeft = new Point(getTopLeft().getX(), getBottomRight().getY());
        Point newBottomRight = new Point(getBottomRight().getX(), getBottomRight().getY() + getHeight());
        return new Rectangle(newTopLeft, newBottomRight, getFillColor(), getBorder());
    }

    @Override
    public Figure createVerticalMirror(){
        Point newTopLeft = new Point(getBottomRight().getX(), getTopLeft().getY());
        Point newBottomRight = new Point(getBottomRight().getX() + getWidth(), getBottomRight().getY());
        return new Rectangle(newTopLeft, newBottomRight, getFillColor(), getBorder());
    }

    @Override
    public boolean equals(Object o){
        return o instanceof Rectangle r && topLeft.equals(r.topLeft) && bottomRight.equals(r.bottomRight);
    }

    @Override
    public boolean contains(Point point){
        return (topLeft.getX() <= point.getX()) && (point.getX() <= bottomRight.getX()) &&
                (topLeft.getY() <= point.getY()) && (point.getY() <= bottomRight.getY());
    }

    @Override
    public void draw(GraphicsContext gc, boolean isSelected){
        gc.save();
        gc.setFill(this.fillColor);
        gc.fillRect(topLeft.getX(), topLeft.getY(), getWidth(), getHeight());
        this.border.apply(gc);
        gc.setStroke(isSelected ? Color.RED : Color.BLACK);
        gc.strokeRect(topLeft.getX(), topLeft.getY(), getWidth(), getHeight());
        gc.restore();
    }

    @Override
    public String toString(){
        return String.format("Rectángulo [%s , %s]", topLeft, bottomRight);
    }

    public Point getTopLeft(){
        return topLeft;
    }

    public Point getBottomRight(){
        return bottomRight;
    }

    /*
     * Funcion privada que calcula el ancho de un rectangulo
     * dados sus dos puntos espaciales.
     */
    private double getWidth(){
        return Math.abs(bottomRight.getX() - topLeft.getX());
    }

    /*
     * Funcion privada que calcula el alto de un rectangulo
     * dados sus dos puntos espaciales.
     */
    private double getHeight(){
        return Math.abs(bottomRight.getY() - topLeft.getY());
    }

    @Override
    public Figure clone(){
        return new Rectangle(topLeft.clone(), bottomRight.clone(), this.fillColor, this.border);
    }

    @Override
    public List<Figure> widthDivide(int n){
        if(n <= 0){
            return List.of();
        }

        List<Figure> newRects = new ArrayList<>();
        double newWidth = getWidth() / n;
        for(int i = 0; i < n; i++){
            Point newTopLeft = new Point(topLeft.getX() + i * newWidth, topLeft.getY());
            Point newBottomRight = new Point(topLeft.getX() + (i + 1) * newWidth, bottomRight.getY());
            newRects.add(new Rectangle(newTopLeft, newBottomRight, this.fillColor, this.border));
        }
        return newRects;
    }

    @Override
    public List<Figure> heightDivide(int n){
        if (n <= 0){
            return List.of();
        }

        List<Figure> newRects = new ArrayList<>();
        double newHeight = getHeight() / n;
        for(int i = 0; i < n; i++){
            Point newTopLeft = new Point(topLeft.getX(), topLeft.getY() + i * newHeight);
            Point newBottomRight = new Point(bottomRight.getX(), topLeft.getY() + (i + 1) * newHeight);
            newRects.add(new Rectangle(newTopLeft, newBottomRight, this.fillColor, this.border));
        }
        return newRects;
    }
}
