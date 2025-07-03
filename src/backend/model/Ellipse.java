package src.backend.model;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

public class Ellipse extends Figure{

    protected final Point centerPoint;
    protected final double sMayorAxis, sMinorAxis;

    public Ellipse(Point center, double sMayorAxis, double sMinorAxis, Color fillColor, Border border){
        super(new Point[]{center}, fillColor, border);
        this.centerPoint = center;
        this.sMayorAxis = sMayorAxis;
        this.sMinorAxis = sMinorAxis;
    }

    @Override
    public boolean equals(Object o){
        return o instanceof Ellipse e && (centerPoint.equals(e.centerPoint)) && (Double.compare(sMayorAxis,e.sMayorAxis) == 0) && (Double.compare(sMinorAxis,e.sMinorAxis) == 0);
    }

    @Override
    protected List<Figure> division(int n, Point dir) {
        return List.of();
    }

    @Override
    public boolean contains(Point point){
        double dx = point.getX() - centerPoint.getX();
        double dy = point.getY() - centerPoint.getY();
        double valor = Math.pow(dx, 2) / Math.pow(sMayorAxis, 2) + Math.pow(dy, 2) / Math.pow(sMinorAxis, 2);
        return valor <= 1;
    }

    @Override
    public void draw(GraphicsContext gc, boolean isSelected){
        gc.save();
        gc.fillOval(centerPoint.getX() - sMayorAxis, centerPoint.getY() - sMinorAxis, sMayorAxis * 2, sMinorAxis * 2);
        gc.strokeOval(centerPoint.getX() - sMayorAxis, centerPoint.getY() - sMinorAxis, sMayorAxis * 2, sMinorAxis * 2);
        this.border.apply(gc);
        gc.setStroke(isSelected ? Color.RED : Color.BLACK);
        gc.strokeOval(centerPoint.getX(), centerPoint.getY(), sMayorAxis, sMinorAxis);
        gc.restore();
    }

    @Override
    public String toString(){
        return String.format("Elipse [Centro: %s, Radio Mayor: %.2f, Radio Menor: %.2f]", centerPoint, sMayorAxis, sMinorAxis);
    }

    @Override
    public Figure multiply() {
        // 1. Crea una nueva elipse con los mismos radios.
        Ellipse clon = new Ellipse(this.centerPoint, this.sMayorAxis, this.sMinorAxis, this.getFillColor(), this.getBorder());

        // 2. Copia las propiedades de formato y efectos. [cite: 411, 484]
//        clon.setPropiedadesFormato(this.getPropiedadesFormato().clonar());
//        clon.setEfectos(this.getEfectos().clonar());

        return clon;
    }
}
