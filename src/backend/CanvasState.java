package src.backend;
import javafx.scene.paint.Color;
import src.backend.model.*;
import src.backend.model.Point;
import java.util.*;
import java.util.List;

public class CanvasState implements Iterable<CanvasFigure>{

    private final List<CanvasFigure> figures = new ArrayList<>();        /* Array de las figuras del lienzo */
    private static final double OFFSET = 15.0;
    private CanvasFigure selectedFigure;     /* Figura seleccionada dentro del lienzo */

    private Color copiedFillColor = null;           /* Color del portapapeles */
    private Border copiedBorder = null;             /* Borde del portapapeles */
    private boolean isCopied = false;               /* Indica si hay un formato copiado */

    private boolean lightening;
    private boolean darkening;
    private boolean vMirror;
    private boolean hMirror;

    private final static double DEFAULT_OFFSET_MULTIPLICATION = 10.0;

    /*
     * Copia el formato de la figura seleccionada.
     * Guarda el valor del color y el borde de la figura.
     */
    public void copy(CanvasFigure figure){
        if(figure != null){
            copiedFillColor = figure.getFillColor();
            copiedBorder = figure.getBorder();
            this.isCopied = true;
        }
    }

    /*
     * Pega el formato de la figura seleccionada.
     */
    public void paste(CanvasFigure figure){
        figure.setBorder(copiedBorder);
        figure.setFillColor(copiedFillColor);
    }

    /*
     * Metodo que le asigna al atributo @selectedFigure la figura donde
     * se contiene el puntero ingresado, o null si no contiene ninguna figura.
     */
    public CanvasFigure selectFigureAtPoint(Point point){
        this.selectedFigure = null;
        boolean found = false;

        /* Recorro la lista de figuras hasta encontrar -o no- la figura ubicada en el punto */
        for (int i = figures.size() - 1; i >= 0 && !found; i--){
            CanvasFigure aux = figures.get(i);
            if(aux.contains(point)){
                this.selectedFigure = aux;
                found = true;
            }
        }
        return selectedFigure;
    }

    public void addFigure(Figure figure){
        figures.add(new CanvasFigure(figure));
    }

//    public void addFigure(CanvasFigure figure){
//        list.add(figure);
//    }

    public void deleteFigure(CanvasFigure figure){
        figures.remove(figure);
    }

    public void wDivide(CanvasFigure figure, int N){
        List<CanvasFigure> aux = new ArrayList<>();

    }

    public void hDivide(CanvasFigure figure, int N){

    }

    public void multiply(CanvasFigure figure, int N){
        if(N > 1 && figures.contains(figure)){
            for(int i = 1; i < N; i++){
                CanvasFigure toMultiply = figure.copy();
                toMultiply.move(i * DEFAULT_OFFSET_MULTIPLICATION, i * DEFAULT_OFFSET_MULTIPLICATION);
                figures.add(toMultiply);
            }
        }
    }


    @Override
    public Iterator<CanvasFigure> iterator(){
        return figures.iterator();
    }
}