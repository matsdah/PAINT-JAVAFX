package src.backend;
import src.backend.model.*;
import src.backend.model.Point;
import java.util.*;
import java.util.List;

public class CanvasState implements Iterable<CanvasFigure>{

    private final List<CanvasFigure> figures = new ArrayList<>();       /* Array de las figuras del lienzo */
    private CanvasFigure selectedFigure;                                /* Figura seleccionada dentro del lienzo */
    private final static double DEFAULT_OFFSET_MULTIPLy = 10.0;         /* Diferencia para pegar la figura al multiplicarla */

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

    public void addFigure(Figure figure, boolean lighten, boolean darken, boolean hMirror, boolean vMirror){
        figures.add(new CanvasFigure(figure, lighten, darken, hMirror, vMirror));
    }

    public void deleteFigure(CanvasFigure figure){
        figures.remove(figure);
    }

    /*
     * Divide la figura seleccionada a lo ancho en N figuras.
     * Elimina la figura original y crea N nuevas.
     */
    public void widthDivide(CanvasFigure figure, int n){
        if(figure == null || n <= 0){
            return;
        }

        List<CanvasFigure> newFigures = figure.widthDivide(n);
        if(!newFigures.isEmpty()){
            figures.remove(figure);
            figures.addAll(newFigures);
        }
    }

    /*
     * Divide la figura seleccionada a lo alto en N figuras.
     * Elimina la figura original y crea     N nuevas.
     */
    public void heightDivide(CanvasFigure figure, int n){
        if(figure == null || n <= 0){
            return;
        }

        List<CanvasFigure> newFigures = figure.heightDivide(n);
        if(!newFigures.isEmpty()){
            figures.remove(figure);
            figures.addAll(newFigures);
        }
    }

    /*
     * Multiplica la figura seleccionada por N.
     * Aparece multiplicada con un offset en el lienzo.
     */
    public void multiply(CanvasFigure figure, int n){
        if (figure == null || n <= 1){
            return;
        }
        for(int i = 1; i < n; i++){
            CanvasFigure copy = figure.copy();
            copy.move(i * DEFAULT_OFFSET_MULTIPLy, i * DEFAULT_OFFSET_MULTIPLy);
            figures.add(copy);
        }
    }

    /*
     * Traslada una figura a una coordenada [X, Y] dentro del lienzo.
     */
    public void moveTo(CanvasFigure figure, Point target){
        if(figure == null || target == null){
            return;
        }
        figure.moveTo(target.getX(), target.getY());
    }

    @Override
    public Iterator<CanvasFigure> iterator(){
        return figures.iterator();
    }
}