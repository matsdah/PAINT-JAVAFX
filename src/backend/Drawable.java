package src.backend;

@FunctionalInterface
public interface Drawable{

    /**
     * Dibuja la figura.
     *
     * @param dc            Contexto de dibujo donde se dibuja la figura.
     * @param isSelected    Indica si el objeto está seleccionado para cambiar el color del borde de la figura.
     */
    void draw(DrawingContext dc, boolean isSelected);
}