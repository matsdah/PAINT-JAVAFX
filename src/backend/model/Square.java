package src.backend.model;

public class Square extends Rectangle{

    public Square(Point corner1, Point corner2, Color fillColor, Border border){
        Square.checkPoints(corner1, corner2);
        double size = Math.max(Math.abs(corner1.getX() - corner2.getX()), Math.abs(corner1.getY() - corner2.getY()));
        super(corner1, new Point(corner1.getX() + size, corner1.getY() + size), fillColor, border);
    }

    @Override
    public String toString(){
        return String.format("Cuadrado [%s, %s]", getTopLeft(), getBottomRight());
    }
}