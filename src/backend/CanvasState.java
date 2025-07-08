package src.backend;
import src.backend.model.*;
import src.backend.model.effects.EffectType;
import java.util.*;
import java.util.function.BiFunction;

public class CanvasState implements Iterable<CanvasFigure>{

    /** Lista de figuras actualmente en el lienzo. */
    private final List<CanvasFigure> figures = new ArrayList<>();

    private CanvasFigure selectedFigure;
    private final static double DEFAULT_OFFSET_MULTIPLY = 10.0;

    public CanvasFigure selectFigureAtPoint(Point point){
        this.selectedFigure = null;
        boolean found = false;

        for(int i = figures.size() - 1; i >= 0 && !found; i--){
            CanvasFigure aux = figures.get(i);
            if(aux.contains(point)){
                this.selectedFigure = aux;
                found = true;
            }
        }
        return selectedFigure;
    }

    /**
     * Construye una coleccion de efectos a partir de los booleanos parametrizados.
     *
     * @param lightened     Si true, aplica un aclaramiento.
     * @param darkened      Si true, aplica un oscurecimiento.
     * @param hMirror       Si true, aplica un espejado horizontal.
     * @param vMirror       Si true, aplica un espejado vertical.
     * @return              Una coleccion de efectos según los parámetros.
     */
    private EnumSet<EffectType> checkEffects(boolean lightened, boolean darkened, boolean hMirror, boolean vMirror){
        EnumSet<EffectType> effects = EnumSet.noneOf(EffectType.class);
        List<Boolean> aux = List.of(lightened, darkened, hMirror, vMirror);
        for(EffectType effect : EffectType.values()){
            if(aux.get(effect.ordinal())){
                effects.add(effect);
            }
        }
        return effects;
    }

    /**
     * Agrega una figura al lienzo con efectos aplicados.
     *
     * @param figure        La figura a agregar dentro del lienzo.
     * @param lightened     Si true, aplica un aclaramiento.
     * @param darkened      Si true, aplica un oscurecimiento.
     * @param hMirror       Si true, aplica un espejado horizontal.
     * @param vMirror       Si true, aplica un espejado vertical.
     */
    public void addFigure(Figure figure, boolean lightened, boolean darkened, boolean hMirror, boolean vMirror){
        figures.add(new CanvasFigure(figure, checkEffects(lightened, darkened, hMirror, vMirror)));
    }

    /**
     * Elimina la figura parametrizada del lienzo.
     *
     * @param figure    La figura del lienzo que se desea eliminar.
     */
    public void deleteFigure(CanvasFigure figure){
        figures.remove(figure);
    }

    private void divideAndReplace(CanvasFigure figure, int n, BiFunction<CanvasFigure, Integer, List<CanvasFigure>> divideFunction){
        checkN(n);
        if(figure == null){
            return;
        }
        List<CanvasFigure> newFigures = divideFunction.apply(figure, n);
        if (!newFigures.isEmpty()) {
            figures.remove(figure);
            figures.addAll(newFigures);
        }
    }

    private void checkN(int n){
        if(n <= 0){
            throw new IllegalArgumentException("¡El numero debe ser un entero positivo!");
        }
    }

    /**
     * Divide horizontalmente una figura en n partes y reemplaza la figura por sus divisiones.
     *
     * @param figure    Figura a dividir dentro del lienzo.
     * @param n         Número de divisiones horizontales.
     */
    public void widthDivide(CanvasFigure figure, int n){
        divideAndReplace(figure, n, CanvasFigure::widthDivide);
    }

    /**
     * Divide verticalmente una figura en n partes y reemplaza la figura por sus divisiones.
     *
     * @param figure    Figura a dividir dentro del lienzo.
     * @param n         Número de divisiones verticales.
     */
    public void heightDivide(CanvasFigure figure, int n){
        divideAndReplace(figure, n, CanvasFigure::heightDivide);
    }

    /**
     * Multiplica una figura en el lienzo por n copias desplazadas
     * por un Offset previamente definido.
     *
     * @param figure    Figura a multiplicar dentro del lienzo.
     * @param n         Número de multiplicaciones de la figura.
     */
    public void multiply(CanvasFigure figure, int n){
        checkN(n);
        if(figure == null){
            return;
        }
        for(int i = 1; i < n; i++){
            CanvasFigure copy = figure.copy();
            copy.move(i * DEFAULT_OFFSET_MULTIPLY, i * DEFAULT_OFFSET_MULTIPLY);
            figures.add(copy);
        }
    }

    /**
     * Mueve espacialmente una figura a un punto especifico con coordenadas [x, y].
     *
     * @param figure    Figura a mover dentro del lienzo.
     * @param target    Punto final con coordenadas [x, y].
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