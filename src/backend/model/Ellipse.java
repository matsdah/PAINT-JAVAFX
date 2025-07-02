package src.backend.model;

import javafx.scene.canvas.GraphicsContext;

public class Ellipse extends Figure{

    protected final Point centerPoint;
    protected final double sMayorAxis, sMinorAxis;

    public Ellipse(Point center, double sMayorAxis, double sMinorAxis){
        super(new Point[]{center});
        this.centerPoint = center;
        this.sMayorAxis = sMayorAxis;
        this.sMinorAxis = sMinorAxis;
    }

    @Override
    public boolean equals(Object o){
        return o instanceof Ellipse other && centerPoint.equals(other.centerPoint) && Double.compare(sMayorAxis,other.sMayorAxis) == 0 && Double.compare(sMinorAxis,other.sMinorAxis) == 0;
    }

    @Override
    public boolean contains(Point point){
        double dx = point.getX() - centerPoint.getX();
        double dy = point.getY() - centerPoint.getY();

        double valor = Math.pow(dx, 2) / Math.pow(sMayorAxis, 2) + Math.pow(dy, 2) / Math.pow(sMinorAxis, 2);

        return valor <= 1;
    }

    @Override
    public void draw(GraphicsContext gc){
        // falta borde
        gc.fillOval(centerPoint.getX() - sMayorAxis, centerPoint.getY() - sMinorAxis, sMayorAxis * 2, sMinorAxis * 2);
        gc.strokeOval(centerPoint.getX() - sMayorAxis, centerPoint.getY() - sMinorAxis, sMayorAxis * 2, sMinorAxis * 2);
    }

    @Override
    public String toString(){
        return String.format("Elipse [Centro: %s, sMayorAxis: %.2f, sMinorAxis: %.2f]", centerPoint, sMayorAxis, sMinorAxis);
    }

    public Point getCenterPoint(){
        return centerPoint;
    }

    public double getsMayorAxis(){
        return sMayorAxis;
    }

    public double getsMinorAxis(){
        return sMinorAxis;
    }
}
