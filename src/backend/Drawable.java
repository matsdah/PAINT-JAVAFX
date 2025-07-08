package src.backend;

@FunctionalInterface
public interface Drawable{
    void draw(DrawingContext dc, boolean isSelected);
}