package backend.model;

public class Circle extends Ellipse implements Figure{

    public Circle(Point centerPoint, double radius) {

        super(centerPoint, 2 * radius, 2*  radius);

    }

    public double getRadius() {
        return sMayorAxis / 2;
    }
}
