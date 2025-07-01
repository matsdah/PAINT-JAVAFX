package backend.model;

public interface Figure{

    private double borderSize;
    private Color borderColor;
    private Color fillColor;
    private StrokeLineCap border;
    private final Point[] points;

    protected Figure(Point[] points){
        this.points = points;
    }

    public void setBorderSize(double borderSize){
        if(borderSize < 0) {
            throw new IllegalArgumentException("El tamaño deL borde debe ser positivo.");
        }
        this.borderSize = borderSize;
    }

    public double getBorderSize(){
        return borderSize;
    }

    public void setFillColor(Color fillColor){
        this.fillColor = fillColor;
    }

    public Color getFillColor(){
        return fillColor;
    }

    public void changeBorder(StrokeLineCap newB);
    public abstract move(int x, int y);
}