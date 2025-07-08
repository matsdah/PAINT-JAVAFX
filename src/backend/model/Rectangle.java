package src.backend.model;
import src.backend.DrawingContext;

import java.util.ArrayList;
import java.util.List;

public class Rectangle extends Figure{

    private final Point topLeft, bottomRight;

    /**
     * Crea un nuevo rectángulo teniendo dos puntos topLeft y bottomRight.
     *
     * @param corner1       Esquina cualquiera del rectángulo.
     * @param corner2       Esquina opuesta.
     * @param fillColor     Color de relleno del rectángulo.
     * @param border        Estilo de borde del rectángulo.
     */
    public Rectangle(Point corner1, Point corner2, Color fillColor, Border border){
        Rectangle.checkPoints(corner1, corner2);
        super(new Point[]{corner1, corner2}, fillColor, border);
        this.topLeft = corner1;
        this.bottomRight = corner2;
    }

    /**
     * Valida y ordena los puntos para que corner1 sea la esquina superior izquierda
     * y corner2 la esquina inferior derecha del rectángulo para dibujarlo correctamente en el lienzo.
     * Ambas esquinar serán modificada si es necesario.
     *
     * @param corner1   Esquina del rectángulo.
     * @param corner2   Esquina opuesta.
     */
    protected static void checkPoints(Point corner1, Point corner2){
        Point auxTL = new Point(Math.min(corner1.getX(), corner2.getX()), Math.min(corner1.getY(), corner2.getY()));
        Point auxBR = new Point(Math.max(corner1.getX(), corner2.getX()), Math.max(corner1.getY(), corner2.getY()));
        corner1.moveTo(auxTL.getX(), auxTL.getY());
        corner2.moveTo(auxBR.getX(), auxBR.getY());
    }

    @Override
    public void drawFill(DrawingContext dc){
        dc.fillRect(topLeft.getX(), topLeft.getY(), getWidth(), getHeight());
    }

    @Override
    public Figure createHorizontalMirror(){
        //Point newTopLeft = new Point(getTopLeft().getX(), getBottomRight().getY());
        //Point newBottomRight = new Point(getBottomRight().getX(), getBottomRight().getY() + getHeight());
        //return new Rectangle(newTopLeft, newBottomRight, getFillColor(), getBorder());

        return createMirror(new Point(0,1));
    }

    @Override
    public Figure createVerticalMirror(){
        //Point newTopLeft = new Point(getBottomRight().getX(), getTopLeft().getY());
        //Point newBottomRight = new Point(getBottomRight().getX() + getWidth(), getBottomRight().getY());
        //return new Rectangle(newTopLeft, newBottomRight, getFillColor(), getBorder());
        return createMirror(new Point(1, 0));
    }

    private Figure createMirror(Point direction){
        Point newTopLeft = new Point(topLeft.getX() + direction.getX() * getWidth(), topLeft.getY() + direction.getY() * getHeight());
        Point newBottomRight = new Point(bottomRight.getX() + getWidth() * direction.getX(), bottomRight.getY() + getHeight() * direction.getY());
        return new Rectangle(newTopLeft, newBottomRight, getFillColor(), getBorder());
    }

    @Override
    public boolean equals(Object o){
        return o instanceof Rectangle r && topLeft.equals(r.topLeft) && bottomRight.equals(r.bottomRight);
    }

    @Override
    public boolean contains(Point point){
        return (topLeft.getX() <= point.getX()) && (point.getX() <= bottomRight.getX()) &&
                (topLeft.getY() <= point.getY()) && (point.getY() <= bottomRight.getY());
    }

    @Override
    public void draw(DrawingContext dc, boolean isSelected){
        dc.save();
        dc.setFill(this.fillColor);
        dc.fillRect(topLeft.getX(), topLeft.getY(), getWidth(), getHeight());
        dc.setStroke(isSelected ? new Color(255, 0, 0, 1.0) : new Color(0, 0, 0, 1.0));
        this.border.apply(dc);
        dc.strokeRect(topLeft.getX(), topLeft.getY(), getWidth(), getHeight());
        dc.restore();
    }

    public Point getTopLeft(){
        return topLeft;
    }

    public Point getBottomRight(){
        return bottomRight;
    }

    private double getWidth(){
        return Math.abs(bottomRight.getX() - topLeft.getX());
    }

    private double getHeight(){
        return Math.abs(bottomRight.getY() - topLeft.getY());
    }

    @Override
    public Figure clone(){
        return new Rectangle(topLeft.clone(), bottomRight.clone(), this.fillColor, this.border);
    }

    @Override
    public List<Figure> widthDivide(int n){
        double aspectRatio = getWidth() / getHeight();
        double newWidth = getWidth() / n;
        double newHeight = newWidth / aspectRatio;
        double centeredY = topLeft.getY() + (getHeight() - newHeight) / 2;

        return divide(n, newHeight, newWidth, new Point(topLeft.getX(), centeredY), new Point(1.0, 0.0));
    }

    @Override
    public List<Figure> heightDivide(int n){
        double aspectRatio = getWidth() / getHeight();
        double newHeight = getHeight() / n;
        double newWidth = newHeight * aspectRatio;
        double centeredX = topLeft.getX() + (getWidth() - newWidth) / 2;

        return divide(n, newHeight, newWidth, new Point(centeredX, topLeft.getY()), new Point(0.0, 1.0));
    }

    protected List<Figure> divide(int n, double newHeight, double newWidth, Point startPoint, Point direction){
        List<Figure> newFigs = new ArrayList<>();
        for(int i = 0; i < n; i++){
            Point newTopLeft = new Point(startPoint.getX() + i * newWidth * direction.getX(), startPoint.getY() + i * newHeight * direction.getY());
            Point newBotRight = new Point(newTopLeft.getX() + newWidth, newTopLeft.getY() + newHeight);
            newFigs.add(new Rectangle(newTopLeft, newBotRight, getFillColor(), getBorder()));
        }
        return newFigs;
    }

    @Override
    public String toString(){
        return String.format("Rectángulo [%s , %s]", topLeft, bottomRight);
    }
}