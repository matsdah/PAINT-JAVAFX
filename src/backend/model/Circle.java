package src.backend.model;
import javafx.scene.paint.Color;

public class Circle extends Ellipse{

    public Circle(Point centerPoint, Point radiusPoint, Color fillColor, Border border){
        double auxX = centerPoint.getX() - radiusPoint.getX();
        double auxY = centerPoint.getY() - radiusPoint.getY();
        double radius = Math.sqrt((auxX * auxX) + (auxY * auxY));

        Point cornerPoint = new Point(centerPoint.getX() + radius, centerPoint.getY() + radius);
        super(centerPoint, cornerPoint, fillColor, border);
    }

    public double getRadius(){
        return difX;
    }

    @Override
    public String toString(){
        return String.format("Círculo [Centro: %s, Radio: %.2f]", centerPoint, getRadius());
    }
}
