package src.backend.model;

/**
 * Se implementa esta clase para no implementar librerías de JavaFX, lo que violaría
 * la correcta separación entre el backend y frontend. La opacidad en el rango [0.0, 1.0].
 *
*/
public class Color{
    private final int red;
    private final int green;
    private final int blue;
    private final double opacity;

    /**
     * Crea un color RGB con cierta opacidad.
     *
     * @param red       Valor numerico del componente rojo.
     * @param green     Valor numerico del componente verde.
     * @param blue      Valor numerico del componente azul.
     * @param opacity   Nivel de opacidad.
     */
    public Color(int red, int green, int blue, double opacity){
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.opacity = opacity;
    }

    public Color(int red, int green, int blue){
        this(red, green, blue, 1.0);
    }

    public int getRed(){
        return red;
    }

    public int getGreen(){
        return green;
    }

    public int getBlue(){
        return blue;
    }

    public double getOpacity(){
        return opacity;
    }

    @Override
    public boolean equals(Object o){
        return o instanceof Color c && red == c.red && green == c.green && blue == c.blue && Double.compare(opacity, c.opacity) == 0;
    }
}
