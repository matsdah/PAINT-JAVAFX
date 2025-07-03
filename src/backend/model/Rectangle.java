package src.backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Rectangle extends Figure{

    private final Point topLeft, bottomRight;

    public Rectangle(Point corner1, Point corner2, Color fillColor, Border border){
        Point auxTL = new Point(Math.min(corner1.getX(), corner2.getX()), Math.min(corner1.getY(), corner2.getY()));
        Point auxBR = new Point(Math.max(corner1.getX(), corner2.getX()), Math.max(corner1.getY(), corner2.getY()));
        super(new Point[]{auxTL, auxBR}, fillColor, border);
        this.topLeft = auxTL;
        this.bottomRight = auxBR;
    } // no hay q verificar que efectiamente top left es top left y top right es top right? SI

    protected Point getTopLeft(Point corner1, Point corner2){
        return new Point(Math.min(corner1.getX(), corner2.getX()), Math.min(corner1.getY(), corner2.getY()));
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
    public void draw(GraphicsContext gc, boolean isSelected){
        gc.save();
        gc.setFill(this.fillColor);
        gc.fillRect(topLeft.getX(), topLeft.getY(), width(), height());
        this.border.apply(gc);
        gc.setStroke(isSelected ? Color.RED : Color.BLACK);
        gc.strokeRect(topLeft.getX(), topLeft.getY(), width(), height());
        gc.restore();
    }

    @Override
    public String toString(){
        return String.format("Rectángulo [%s , %s]", topLeft, bottomRight);
    }

    public Point getTopLeft(){
        return topLeft;
    }

    public Point getBottomRight(){
        return bottomRight;
    }

    /*
     * Funcion privada que calcula el ancho de un rectangulo
     * dados sus dos puntos espaciales.
     */
    private double width(){
        return Math.abs(bottomRight.getX() - topLeft.getX());
    }

    /*
     * Funcion privada que calcula el alto de un rectangulo
     * dados sus dos puntos espaciales.
     */
    private double height(){
        return Math.abs(bottomRight.getY() - topLeft.getY());
    }

    @Override
    public Figure multiply() {
        // Crea un nuevo rectángulo con las mismas dimensiones.
        Rectangle clon = new Rectangle(this.topLeft, this.bottomRight, this.getFillColor(), this.getBorder());

        // 2. Copia las propiedades de formato y efectos (es crucial que estos
        //    objetos también tengan un metodo para ser clonados).
        //    Esto cumple con los requisitos del enunciado[cite: 411, 484].
//        clon.setPropiedadesFormato(this.getPropiedadesFormato().clonar());
//        clon.setEfectos(this.getEfectos().clonar());

        return clon;
    }


    @Override
    public List<Figure> division(int n, Point dir){
        List<Figure> aux = new ArrayList<>();
        double auxWidth = width() / n;

        for(int i = 0; i < n; i++){


        }

        return aux;
    }



}
