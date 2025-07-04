package src.backend;

public interface Movable{
    void move(double deltaX, double deltaY);    /* Metodo para desplazar una figura respecto su ubicacion relativa en el lienzo */
    void moveTo(double x, double y);            /* Metodo para trasladar una figura dentro del lienzo */
}