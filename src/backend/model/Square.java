package src.backend.model;

import javafx.scene.paint.Color;

public class Square extends Rectangle{

    public Square(Point topLeft, double size, Color fillColor, Border border){
        super(topLeft, new Point(topLeft.getX() + validateSquareSize(size), topLeft.getY() + size), fillColor, border);
    }

    /*
     * Funcion privada que verifica que el argumento parametrizado
     * en el constructor sea valido. Lanza una excecpion en caso de que no lo sea.
     */
    private static double validateSquareSize(double size){
        if(Double.compare(0,size) <= 0){
            throw new IllegalArgumentException("El tamaño del cuadrado debe ser positivo.");
        }
        return size;
    }

    @Override
    public String toString(){
        return String.format("Cuadrado [%s, %s]", getTopLeft(), getBottomRight());
    }
}