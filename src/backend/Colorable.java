package src.backend;
import src.backend.model.Color;

/* Interfaz para remplazar la libreria Color de JavaFX */
public interface Colorable{

    /**
     * Establece el color de relleno de la figura.
     *
     * @param fillColor Color de relleno a reemplazar.
     */
    void setFillColor(Color fillColor);

    /**
     * Obtiene el color de relleno actual de la figura.
     *
     * @return  Color de relleno.
     */
    Color getFillColor();
}