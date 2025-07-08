package src.backend.model;

/**
 *  Interfaz funcional para relacionar la creacion de una figura entre el backend y el frontend
 */
@FunctionalInterface
public interface FigureFactory{

    /**
     * Crea una nueva figura utilizando los parámetros especificados.
     *
     * @param p1            Primer punto que define la figura.
     * @param p2            Segundo punto que define la figura.
     * @param fillColor     Color de relleno de la figura.
     * @param bd            Tipo de borde de la figura.
     * @return              Una nueva instancia Figure.
     */
    Figure create(Point p1, Point p2, Color fillColor, Border bd);
}