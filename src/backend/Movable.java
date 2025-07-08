package src.backend;

public interface Movable{

    /**
     * Desplaza el objeto desde su posición relativa actual.
     *
     * @param deltaX    Desplazamiento en el eje x.
     * @param deltaY    Desplazamiento en el eje y.
     */
    void move(double deltaX, double deltaY);

    /**
     * Mueve el objeto a una coordenada [x, y] dentro del lienzo.
     *
     * @param x         Nueva coordenada x.
     * @param y         Nueva coordenada y.
     */
    void moveTo(double x, double y);
}