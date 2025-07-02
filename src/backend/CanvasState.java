package src.backend;

import javafx.scene.paint.Color;
import src.backend.model.*;
import src.backend.model.Point;
import java.util.*;
import java.util.List;

public class CanvasState implements Iterable<CanvasFigure>{

    private final List<CanvasFigure> list = new ArrayList<>();        /* Array de las figuras del lienzo */

    private CanvasFigure selectedFigure = null;     /* Figura seleccionada dentro del lienzo */

    private Color copiedFillColor = null;           /* Color del portapapeles */
    private Border copiedBorder = null;             /* Borde del portapapeles */
    private boolean isCopied;                       /* Indica si hay un formato copiado */

    private boolean lightening;
    private boolean darkening;
    private boolean vMirror;
    private boolean hMirror;

    /*
     * Copia el formato de la figura seleccionada.
     */
    /*public Figure copy(Figure figure){

    }*/

    /*
     * Pega el formato de la figura seleccionada.
     */
    public void paste(Figure figure){

    }

    /*
     * Metodo que le asigna al atributo @selectedFigure la figura donde
     * se contiene el puntero ingresado, o null si no contiene ninguna figura.
     */
    public CanvasFigure selectFigureAtPoint(Point point){
        this.selectedFigure = null;
        boolean found = false;

        /* Recorro la lista de figuras hasta encontrar -o no- la figura ubicada en el punto */
        for (int i = list.size() - 1; i >= 0 && !found; i--){
            CanvasFigure aux = list.get(i);
            if(aux.contains(point)){
                this.selectedFigure = aux;
                found = true;
            }
        }
        return selectedFigure;
    }

    public void addFigure(Figure figure){
        list.add(new CanvasFigure(figure));
    }

    public void addFigure(CanvasFigure figure){
        list.add(figure);
    }

    public void deleteFigure(CanvasFigure figure){
        list.remove(figure);
    }

    public void wDivide(Figure figure, int N){

    }

    public void hDivide(Figure figure, int N){

    }

    public void multiply(Figure figure, int N){

    }

    public void translate(int x, int y){

    }

    @Override
    public Iterator<CanvasFigure> iterator(){
        return list.iterator();
    }
}
