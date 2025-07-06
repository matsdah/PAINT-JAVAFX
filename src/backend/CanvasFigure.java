package src.backend;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import src.backend.model.*;
import src.backend.model.effects.EffectType;
import java.util.*;

public class CanvasFigure implements Movable, Drawable{
    private final Figure fig;
    private final EnumSet<EffectType> effects;

    public CanvasFigure(Figure fig, EnumSet<EffectType> effects){
        this.fig = fig;
        this.effects = EnumSet.copyOf(effects);
    }

    public boolean hasEffect(EffectType effect){
        return effects.contains(effect);
    }

    public void addEffect(EffectType eff){
        effects.add(eff);
    }

    public void removeEffect(EffectType eff){
        effects.remove(eff);
    }

    @Override
    public void move(double deltaX, double deltaY){
        fig.move(deltaX, deltaY);
    }

    public boolean contains(Point point){
        return fig.contains(point);
    }

    public void setFillColor(Color color){
        fig.setFillColor(color);
    }

    public void setBorder(Border border){
        fig.setBorder(border);
    }

    public Color getFillColor(){
        return fig.getFillColor();
    }

    public Border getBorder(){
        return fig.getBorder();
    }

    @Override
    public void draw(GraphicsContext gc, boolean isSelected){
        fig.draw(gc, isSelected);
        for(EffectType eff: effects){
            eff.apply(gc, fig);
        }
    }

    @Override
    public void moveTo(double x, double y){
        fig.moveTo(x, y);
    }

    private List<CanvasFigure> wrapFigures(List<Figure> figures){
        List<CanvasFigure> canvasFigures = new ArrayList<>();
        for(Figure figure : figures){
            canvasFigures.add(new CanvasFigure(figure, effects));
        }
        return canvasFigures;
    }

    public List<CanvasFigure> widthDivide(int n){
        return wrapFigures(fig.widthDivide(n));
    }

    public List<CanvasFigure> heightDivide(int n){
        return wrapFigures(fig.heightDivide(n));
    }

    public CanvasFigure copy(){
        Figure copiedFig = fig.clone();
        return new CanvasFigure(copiedFig, effects);
    }

    @Override
    public String toString(){
        return fig.toString();
    }
}