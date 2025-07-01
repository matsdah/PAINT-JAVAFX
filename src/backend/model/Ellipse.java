package backend.model;

public class Ellipse implements Figure{

    protected final Point centerPoint;
    protected final double sMayorAxis, sMinorAxis;

    public Ellipse(Point center, double sMayorAxis, double sMinorAxis) {
        super(new Point[]{center});
        this.center = center;
        this.sMayorAxis = sMayorAxis;
        this.sMinorAxis = sMinorAxis;
    }
    
    @Override
    public void move(int x, int y){

    }

    @Override
    public boolean equals(Object o){

    }

    public Point getCenterPoint() {
        return centerPoint;
    }
    public double getsMayorAxis() {
        return sMayorAxis;
    }
    public double getsMinorAxis() {
        return sMinorAxis;
    }
}
