package src.backend.model;
import src.backend.DrawingContext;
import java.util.ArrayList;
import java.util.List;

public class Ellipse extends Figure{

    protected final Point centerPoint;
    protected final double difX, difY;

    public Ellipse(Point center, Point radiusPoint, Color fillColor, Border border){
        super(new Point[]{center}, fillColor, border);
        this.centerPoint = center;
        this.difX = Math.abs(center.getX() - radiusPoint.getX());
        this.difY = Math.abs(center.getY() - radiusPoint.getY());
    }

    @Override
    public void drawFill(DrawingContext dc){
        dc.fillOval(centerPoint.getX() - difX, centerPoint.getY() - difY, difX * 2, difY * 2);
    }

    @Override
    public Figure createHorizontalMirror(){
        Point newCenter = new Point(centerPoint.getX(), centerPoint.getY() + (difY * 2));
        Point newRadiusPoint = new Point(newCenter.getX() + difX, newCenter.getY() + difY);
        return new Ellipse(newCenter, newRadiusPoint, getFillColor(), getBorder());
    }

    @Override
    public Figure createVerticalMirror(){
        Point newCenter = new Point(centerPoint.getX() + (difX * 2), centerPoint.getY());
        Point newRadiusPoint = new Point(newCenter.getX() + difX, newCenter.getY() + difY);
        return new Ellipse(newCenter, newRadiusPoint, getFillColor(), getBorder());
    }

    @Override
    public boolean equals(Object o){
        return o instanceof Ellipse e && (centerPoint.equals(e.centerPoint)) && (Double.compare(difX, e.difX) == 0) && (Double.compare(difY, e.difY) == 0);
    }

    @Override
    public boolean contains(Point point){
        double dx = point.getX() - centerPoint.getX();
        double dy = point.getY() - centerPoint.getY();
        double valor = Math.pow(dx, 2) / Math.pow(difX, 2) + Math.pow(dy, 2) / Math.pow(difY, 2);
        return valor <= 1;
    }

    @Override
    public void draw(DrawingContext dc, boolean isSelected){
        dc.save();
        dc.setFill(this.fillColor);
        dc.fillOval(centerPoint.getX() - difX, centerPoint.getY() - difY, difX * 2, difY * 2);
        dc.setStroke(isSelected ? new Color(255, 0, 0, 1.0) : new Color(0, 0, 0, 1.0));
        this.border.apply(dc);
        dc.strokeOval(centerPoint.getX() - difX, centerPoint.getY() - difY, difX * 2, difY * 2);
        dc.restore();
    }

    @Override
    public void moveTo(double x, double y){
        double newCenterX = x + this.difX;
        double newCenterY = y + this.difY;
        super.moveTo(newCenterX, newCenterY);
    }

    @Override
    public List<Figure> widthDivide(int n){
        if (n <= 0) {
            return null;
        }
        List<Figure> newEllipses = new ArrayList<>();
        double aspectRatio = this.difX / this.difY;
        double newRadiusX = this.difX / n;
        double newRadiusY = newRadiusX / aspectRatio;
        double originalLeftX = centerPoint.getX() - this.difX;
        double centeredY = centerPoint.getY() - this.difY + (this.difY * 2 - newRadiusY * 2) / 2;

        for (int i = 0; i < n; i++) {
            Point newCenter = new Point(originalLeftX + newRadiusX + (i * 2 * newRadiusX), centeredY + newRadiusY);
            Point newRadiusPoint = new Point(newCenter.getX() + newRadiusX, newCenter.getY() + newRadiusY);
            newEllipses.add(new Ellipse(newCenter, newRadiusPoint, this.fillColor, this.border));
        }

        return newEllipses;
    }

    @Override
    public List<Figure> heightDivide(int n){
        if (n <= 0) {
            return null;
        }
        List<Figure> newEllipses = new ArrayList<>();
        double aspectRatio = this.difX / this.difY;
        double newRadiusY = this.difY / n;
        double newRadiusX = newRadiusY * aspectRatio;
        double originalTopY = centerPoint.getY() - this.difY;
        double centeredX = centerPoint.getX() - this.difX + (this.difX * 2 - newRadiusX * 2) / 2;

        for (int i = 0; i < n; i++) {
            Point newCenter = new Point(centeredX + newRadiusX, originalTopY + newRadiusY + (i * 2 * newRadiusY));
            Point newRadiusPoint = new Point(newCenter.getX() + newRadiusX, newCenter.getY() + newRadiusY);
            newEllipses.add(new Ellipse(newCenter, newRadiusPoint, this.fillColor, this.border));
        }

        return newEllipses;
    }

    @Override
    public Figure clone(){
        Point radiusPoint = new Point(centerPoint.getX() + difX, centerPoint.getY() + difY);
        return new Ellipse(centerPoint.clone(), radiusPoint, this.fillColor, this.border);
    }

    @Override
    public String toString(){
        return String.format("Elipse [Centro: %s, Radio Mayor: %.2f, Radio Menor: %.2f]", centerPoint, Math.max(difX, difY), Math.min(difX, difY));
    }
}