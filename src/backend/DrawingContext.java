package src.backend;
import src.backend.model.Color;
import src.backend.model.LineCap;

/* Interfaz para reemplazar GraphicContent de JavaFX */
public interface DrawingContext{
    void setFill(Color color);
    void setStroke(Color color);
    void setLineWidth(double width);
    void setLineCap(LineCap cap);
    void setLineDashes(double... dashes);
    void fillRect(double x, double y, double width, double height);
    void strokeRect(double x, double y, double width, double height);
    void fillOval(double x, double y, double width, double height);
    void strokeOval(double x, double y, double width, double height);
    void save();
    void restore();
}