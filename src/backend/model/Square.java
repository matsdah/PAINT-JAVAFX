package backend.model;

public class Square extends Rectangle implements Figure{

    public Square(Point topLeft, double size) {
        super(topLeft, new Point(topLeft.getX() + validateSquareSize(size), topLeft.getY() + size));
    }
}