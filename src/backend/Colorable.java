package src.backend;
import src.backend.model.Color;

/* Interfaz para remplazar la libreria Color de JavaFX */
public interface Colorable{
    void setFillColor(Color fillColor);
    Color getFillColor();
}