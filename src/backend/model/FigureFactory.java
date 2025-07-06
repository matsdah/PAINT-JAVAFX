package src.backend.model;
import javafx.scene.paint.Color;

@FunctionalInterface
public interface FigureFactory{
    Figure create(Point p1, Point p2, Color fillColor, Border bd);
}