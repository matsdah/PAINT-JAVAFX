package src.backend;
import src.backend.model.*;
import src.backend.model.effects.EffectType;
import java.util.*;

public class CanvasState implements Iterable<CanvasFigure>{

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

    public void addFigure(Figure figure, boolean lighten, boolean darken, boolean hMirror, boolean vMirror){
        figures.add(new CanvasFigure(figure, checkEffects(lighten, darken, hMirror, vMirror)));
    }

    public void deleteFigure(CanvasFigure figure){
        figures.remove(figure);
    }

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

    public void multiply(CanvasFigure figure, int n){
        if(figure == null || n <= 1){
            return;
        }
        for(int i = 1; i < n; i++){
            CanvasFigure copy = figure.copy();
            copy.move(i * DEFAULT_OFFSET_MULTIPLY, i * DEFAULT_OFFSET_MULTIPLY);
            figures.add(copy);
        }
    }

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