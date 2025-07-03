package src.backend.model;
import javafx.scene.paint.Color;

public class Square extends Rectangle{

    //Chequear como hacer para no repetir codigo con los auxTL y auxBR
    public Square(Point corner1, Point corner2, Color fillColor, Border border){
        Point auxTL = new Point(Math.min(corner1.getX(), corner2.getX()), Math.min(corner1.getY(), corner2.getY()));
        Point auxBR = new Point(Math.max(corner1.getX(), corner2.getX()), Math.max(corner1.getY(), corner2.getY()));
        double size = Math.max(Math.abs(auxTL.getX() - auxBR.getX()), Math.abs(auxTL.getY() - auxBR.getY()));
        super(auxTL, new Point(auxTL.getX() + size, auxTL.getY() + size), fillColor, border);
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