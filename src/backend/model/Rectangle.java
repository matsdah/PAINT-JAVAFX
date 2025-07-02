package src.backend.model;

public class Rectangle extends Figure{

    private final Point topLeft, bottomRight;

    public Rectangle(Point topLeft, Point bottomRight) {
        super(new Point[]{topLeft, bottomRight});
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    @Override
    public move(int x, int y){

    }

    @Override
    public boolean equals(Object o){

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
