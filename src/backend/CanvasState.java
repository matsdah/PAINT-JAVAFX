package backend;

import backend.model.Figure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CanvasState{

    private final Collection<Figure> list = new ArrayList<>();

    private boolean lightening;
    private boolean darkening;
    private boolean vMirror;
    private boolean hMirror;
    private boolean isCopied;           /* Indica si hay un formato copiado */
    private figure Figure;              /* Figura que guarda el formato copiado */

    public Figure copy(Figure figure){

    }

    public void paste(Figure figure){

    }

    public void addFigure(Figure figure) {
        list.add(figure);
    }

    public void deleteFigure(Figure figure) {
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

    public Iterable<Figure> figures() {
        return List.copyOf(list);
    }
}
