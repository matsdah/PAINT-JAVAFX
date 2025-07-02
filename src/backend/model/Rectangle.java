package src.backend.model;

import javafx.scene.canvas.GraphicsContext;

public class Rectangle extends Figure{

    private final Point topLeft, bottomRight;

    public Rectangle(Point topLeft, Point bottomRight){
        super(new Point[]{topLeft, bottomRight});
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    } // no hay q verificar que efectiamente top left es top left y top right es top right?

    @Override
    public boolean equals(Object o){
        return o instanceof Rectangle other && topLeft.equals(other.topLeft) && bottomRight.equals(other.bottomRight);
    }

    @Override
    public boolean contains(Point point){
        return topLeft.getX() <= point.getX() && point.getX() <= bottomRight.getX() &&
                topLeft.getY() <= point.getY() && point.getY() <= bottomRight.getY();
    }

    @Override
    public void draw(GraphicsContext gc) {
        // falta borde
        gc.fillRect(topLeft.getX(), topLeft.getY(), width(), height());
        gc.strokeRect(topLeft.getX(), topLeft.getY(), width(), height());
    }

    @Override
    public String toString(){
        return String.format("Rectángulo [ %s , %s ]", topLeft, bottomRight);
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    public double width() {
        return bottomRight.getX() - topLeft.getX();
    }

    public double height() {
        return bottomRight.getY() - topLeft.getY();
    }
}
