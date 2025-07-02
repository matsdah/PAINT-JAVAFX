package src.backend.model;

public class Square extends Rectangle{

    public Square(Point topLeft, double size){
        super(topLeft, new Point(topLeft.getX() + validateSquareSize(size), topLeft.getY() + size));
    }

    private static double validateSquareSize(double size){
        if(Double.compare(0,size) <= 0){
            throw new IllegalArgumentException("Square size cannot be negative or 0");
        }
        return size;
    }

    @Override
    public String toString(){
        return String.format("Cuadrado [ %s, %s ]", getTopLeft(), getBottomRight());
    }
}