package src.backend.model;

/**
 * Define los estilos posibles para los extremos de líneas dibujadas.
 * Evita el uso de StrokeLineCap que pertenece a JavaFX para separar
 * correctamente el backend del frontend.
 */
public enum LineCap{
    BUTT,
    ROUND,
    SQUARE
}