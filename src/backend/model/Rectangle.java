package src.backend.model;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class Rectangle extends Figure{

    private final Point topLeft, bottomRight;

    public Rectangle(Point corner1, Point corner2, Color fillColor, Border border){
        Rectangle.checkPoints(corner1, corner2);
        super(new Point[]{corner1, corner2}, fillColor, border);
        this.topLeft = corner1;
        this.bottomRight = corner2;
    }

    protected static void checkPoints(Point corner1, Point corner2){
        Point auxTL = new Point(Math.min(corner1.getX(), corner2.getX()), Math.min(corner1.getY(), corner2.getY()));
        Point auxBR = new Point(Math.max(corner1.getX(), corner2.getX()), Math.max(corner1.getY(), corner2.getY()));
        corner1.moveTo(auxTL.getX(), auxTL.getY());
        corner2.moveTo(auxBR.getX(), auxBR.getY());
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

    public Point getTopLeft(){
        return topLeft;
    }

    public Point getBottomRight(){
        return bottomRight;
    }

    /*
     * Calcula el ancho de un rectangulo dados sus dos puntos en el lienzo.
     */
    private double getWidth(){
        return Math.abs(bottomRight.getX() - topLeft.getX());
    }

    /*
     * Calcula el alto de un rectangulo dados sus dos puntos en el lienzo.
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
            return null;
        }
        List<Figure> newRects = new ArrayList<>();
        double originalWidth = getWidth();
        double originalHeight = getHeight();
        double aspectRatio = originalWidth / originalHeight;
        double newWidth = originalWidth / n;
        double newHeight = newWidth / aspectRatio;
        double centeredY = topLeft.getY() + (originalHeight - newHeight) / 2;

        for(int i = 0; i < n; i++){
            Point newTopLeft = new Point(topLeft.getX() + i * newWidth, centeredY);
            Point newBottomRight = new Point(newTopLeft.getX() + newWidth, newTopLeft.getY() + newHeight);
            newRects.add(new Rectangle(newTopLeft, newBottomRight, getFillColor(), getBorder()));
        }
        return newRects;
    }

    @Override
    public List<Figure> heightDivide(int n){
        if(n <= 0){
            return null;
        }
        List<Figure> newRects = new ArrayList<>();
        double originalWidth = getWidth();
        double originalHeight = getHeight();
        double aspectRatio = originalWidth / originalHeight;
        double newHeight = originalHeight / n;
        double newWidth = newHeight * aspectRatio;
        double centeredX = topLeft.getX() + (originalWidth - newWidth) / 2;

        for(int i = 0; i < n; i++){
            Point newTopLeft = new Point(centeredX, topLeft.getY() + i * newHeight);
            Point newBottomRight = new Point(newTopLeft.getX() + newWidth, newTopLeft.getY() + newHeight);
            newRects.add(new Rectangle(newTopLeft, newBottomRight, getFillColor(), getBorder()));
        }
        return newRects;
    }

    @Override
    public String toString(){
        return String.format("Rectángulo [%s , %s]", topLeft, bottomRight);
    }
}