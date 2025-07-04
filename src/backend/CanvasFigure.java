package src.backend;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import src.backend.model.*;
import src.backend.model.effects.EffectType;
import src.backend.model.effects.FigureEffect;

import java.util.*;

public class CanvasFigure implements Movable, Drawable{
    private final Figure fig;           /* Figura principal */

/*
    private final EnumSet<EffectType> effects;
*/

    /* Efectos de la figura */
    private boolean lightenEffect;
    private boolean darkenEffect;
    private boolean horizontalMirrorEffect;
    private boolean verticalMirrorEffect;

    public CanvasFigure(Figure fig, boolean lighten, boolean darken, boolean hMirror, boolean vMirror){
        this.fig = fig;
        this.lightenEffect = lighten;
        this.darkenEffect = darken;
        this.horizontalMirrorEffect = hMirror;
        this.verticalMirrorEffect = vMirror;
    }

/*
    public CanvasFigure(Figure fig, EnumSet<EffectType> effects){
        this.fig = fig;
        this.effects = new HashSet<>(effects);
    }
*/
/*

    public boolean hasEffect(EffectType effect){
        return effects.contains(effect);
    }

    public void addEffect(EffectType eff){
        effects.add(eff);
    }

*/


    /* Setters y getters para los efectos de la figura */
    public boolean hasLightenEffect(){
        return lightenEffect;
    }

    public void setLightenEffect(boolean lightenEffect){
        this.lightenEffect = lightenEffect;
    }

    public boolean hasDarkenEffect(){
        return darkenEffect;
    }

    public void setDarkenEffect(boolean darkenEffect){
        this.darkenEffect = darkenEffect;
    }

    public boolean hasHorizontalMirrorEffect(){
        return horizontalMirrorEffect;
    }

    public void setHorizontalMirrorEffect(boolean horizontalMirrorEffect){
        this.horizontalMirrorEffect = horizontalMirrorEffect;
    }

    public boolean hasVerticalMirrorEffect(){
        return verticalMirrorEffect;
    }

    public void setVerticalMirrorEffect(boolean verticalMirrorEffect){
        this.verticalMirrorEffect = verticalMirrorEffect;
    }

    /*
     * Mueve la figura junto a todos los efectos. Cada figura debe saber moverse.
     */
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


        if(lightenEffect){
            gc.save();
            gc.setFill(Color.rgb(255, 255, 255, 0.7));
            fig.drawFill(gc);
            gc.restore();
        }
        if(darkenEffect){
            gc.save();
            gc.setFill(Color.rgb(0, 0, 0, 0.3));
            fig.drawFill(gc);
            gc.restore();
        }
        if(horizontalMirrorEffect){
            fig.createHorizontalMirror().draw(gc, false);
        }
        if(verticalMirrorEffect){
            fig.createVerticalMirror().draw(gc, false);
        }
    }

    @Override
    public void moveTo(double x, double y){
        fig.moveTo(x, y);
    }

    private List<CanvasFigure> wrapFigures(List<Figure> figures){
        List<CanvasFigure> canvasFigures = new ArrayList<>();
        for(Figure figure : figures){
            canvasFigures.add(new CanvasFigure(figure, this.hasLightenEffect(), this.hasDarkenEffect(), this.hasHorizontalMirrorEffect(), this.hasVerticalMirrorEffect()));
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
        return new CanvasFigure(copiedFig, this.hasLightenEffect(), this.hasDarkenEffect(), this.hasHorizontalMirrorEffect(), this.hasVerticalMirrorEffect());
    }

    @Override
    public String toString(){
        return fig.toString();
    }
}