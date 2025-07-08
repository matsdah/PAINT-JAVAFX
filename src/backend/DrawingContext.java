package src.backend;
import src.backend.model.Color;
import src.backend.model.LineCap;

/**
 * Interfaz para reemplazar GraphicContent de JavaFX.
 * Define las operaciones para dibujar figuras con tamaño, colores y bordes.
 */
public interface DrawingContext{

    /** Asigna el ancho del trazo del borde. */
    void setFill(Color color);

    /** Asigna el color de trazo. */
    void setStroke(Color color);

    /** Asigna el ancho de línea para el trazo. */
    void setLineWidth(double width);

    /** Asigna el estilo de los extremos de línea (BUTT, ROUND, SQUARE). */
    void setLineCap(LineCap cap);

    /** Asigna el patrón de líneas discontinuas para el trazo. */
    void setLineDashes(double... dashes);

    /** Dibuja un rectángulo relleno. */
    void fillRect(double x, double y, double width, double height);

    /** Dibuja el borde de un rectángulo. */
    void strokeRect(double x, double y, double width, double height);

    /** Dibuja un óvalo relleno. */
    void fillOval(double x, double y, double width, double height);

    /** Dibuja el borde de un óvalo. */
    void strokeOval(double x, double y, double width, double height);

    /** Guarda el estado actual del contexto gráfico. */
    void save();

    /** Restaura el último estado guardado del contexto gráfico. */
    void restore();

}