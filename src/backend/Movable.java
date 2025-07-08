package src.backend;

public interface Movable{
    void move(double deltaX, double deltaY);
    void moveTo(double x, double y);
}