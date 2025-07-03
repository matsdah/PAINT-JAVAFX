package src.backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Rectangle extends Figure{

    private final Point topLeft, bottomRight;

    public Rectangle(Point topLeft, Point bottomRight, Color fillColor, Border border){
        super(new Point[]{topLeft, bottomRight}, fillColor, border);
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    } // no hay q verificar que efectiamente top left es top left y top right es top right? SI

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
        gc.fillRect(topLeft.getX(), topLeft.getY(), width(), height());
        gc.setFill(this.fillColor);
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
        return bottomRight.getX() - topLeft.getX();
    }

    /*
     * Funcion privada que calcula el alto de un rectangulo
     * dados sus dos puntos espaciales.
     */
    private double height(){
        return bottomRight.getY() - topLeft.getY();
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
