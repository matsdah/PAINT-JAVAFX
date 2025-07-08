package src.backend.model;

public class Color{
    private final int red;
    private final int green;
    private final int blue;
    private final double opacity;

    public Color(int red, int green, int blue, double opacity){
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.opacity = opacity;
    }

    /* Constructor con opacidad al 100% */
    public Color(int red, int green, int blue){
        this(red, green, blue, 1.0);
    }

    /* Getters */
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
    public boolean equals(Object o) {
        return o instanceof Color c && red == c.red && green == c.green && blue == c.blue && Double.compare(opacity, c.opacity) == 0;
    }
}
