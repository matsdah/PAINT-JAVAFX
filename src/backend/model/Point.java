package src.backend.model;
import src.backend.Movable;
import java.util.Objects;

public class Point implements Movable{

    private double x, y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    /* Getters */
    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public Point clone(){
        return new Point(this.x, this.y);
    }

    @Override
    public void move(double deltaX, double deltaY){
        x += deltaX;
        y += deltaY;
    }

    @Override
    public void moveTo(double x, double y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return "Pointer at: [%.2f : %.2f]".formatted(x, y);
    }

    @Override
    public boolean equals(Object o){
        return o instanceof Point p && (Double.compare(p.getX(), x) == 0) && Double.compare(p.getY(), y) == 0;
    }

    @Override
    public int hashCode(){
        return Objects.hash(x, y);
    }
}