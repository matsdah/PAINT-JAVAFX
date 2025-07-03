package src.backend.model;
import javafx.scene.paint.Color;

public class Circle extends Ellipse{

    public Circle(Point centerPoint, double radius, Color fillColor, Border border){
        super(centerPoint, 2 * radius, 2*  radius, fillColor, border);
    }

    public double getRadius(){
        return sMayorAxis / 2;
    }

    @Override
    public String toString(){
        return String.format("Círculo [Centro: %s, Radio: %.2f]", centerPoint, getRadius());
    }
}
