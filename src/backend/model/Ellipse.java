package src.backend.model;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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
    public boolean equals(Object o){
        return o instanceof Ellipse e && (centerPoint.equals(e.centerPoint)) && (Double.compare(difX, e.difX) == 0) && (Double.compare(difY, e.difY) == 0);
    }

    @Override
    protected List<Figure> division(int n, Point dir) {
        return List.of();
    }

    @Override
    public boolean contains(Point point){
        double dx = point.getX() - centerPoint.getX();
        double dy = point.getY() - centerPoint.getY();
        double valor = Math.pow(dx, 2) / Math.pow(difX, 2) + Math.pow(dy, 2) / Math.pow(difY, 2);
        return valor <= 1;
    }

    @Override
    public void draw(GraphicsContext gc, boolean isSelected){
        gc.save();
        gc.setFill(this.fillColor);
        gc.fillOval(centerPoint.getX() - difX, centerPoint.getY() - difY, difX * 2, difY * 2);
        this.border.apply(gc);
        gc.setStroke(isSelected ? Color.RED : Color.BLACK);
        gc.strokeOval(centerPoint.getX() - difX, centerPoint.getY() - difY, difX * 2, difY * 2);
        gc.restore();
    }

    @Override
    public String toString(){
        return String.format("Elipse [Centro: %s, Radio Mayor: %.2f, Radio Menor: %.2f]", centerPoint, Math.max(difX, difY), Math.min(difX, difY));
    }

    @Override
    public Figure multiply() {
        // 1. Crea una nueva elipse con los mismos radios.
        Ellipse clon = new Ellipse(this.centerPoint, new Point(centerPoint.getX() + (difX / 2), centerPoint.getY() + (difY / 2)), this.getFillColor(), this.getBorder());

        // 2. Copia las propiedades de formato y efectos. [cite: 411, 484]
        // clon.setPropiedadesFormato(this.getPropiedadesFormato().clonar());
        // clon.setEfectos(this.getEfectos().clonar());

        return clon;
    }


}