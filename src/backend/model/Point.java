package src.backend.model;
import src.backend.Movable;
import java.util.Objects;

public class Point implements Movable{

    private double x, y;

    /**
     * Crea un nuevo punto con las coordenadas [x, y].
     *
     * @param x Coordenada horizontal del punto.
     * @param y Coordenada vertical del punto.
     */
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

    /**
     * Crea una copia del punto actual con las mismas caracteristicas.
     *
     * @return     Un nuevo punto con las mismas coordenadas.
     */
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
        return "Puntero en: [%.2f : %.2f]".formatted(x, y);
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