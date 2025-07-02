package src.backend.model;

import src.backend.Movable;

public class Point implements Movable {

    private double x, y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    @Override
    public void move(double deltaX, double deltaY){
        x += deltaX;
        y += deltaY;
    }

    @Override
    public String toString(){
        return "Pointer at: [ %.2f : %.2f ]".formatted(x, y);
    }
}
