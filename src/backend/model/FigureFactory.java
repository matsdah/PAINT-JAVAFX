package src.backend.model;

public interface FigureFactory{
    Figure create(Point p1, Point p2, Color fillColor, Border bd);
}