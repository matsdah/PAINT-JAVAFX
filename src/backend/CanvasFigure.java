package src.backend;
import src.backend.model.*;
import src.backend.model.EffectType;
import java.util.*;

public class CanvasFigure implements Movable, Drawable{
    private final Figure fig;

    /** Conjunto de efectos aplicados a la figura. */
    private final EnumSet<EffectType> effects;

    /**
     * Construye una nueva figura en el lienzo con ciertos efectos.
     *
     * @param fig       Figura representada.
     * @param effects   Conjunto de efectos a aplicar.
     */
    public CanvasFigure(Figure fig, EnumSet<EffectType> effects){
        this.fig = fig;
        this.effects = EnumSet.copyOf(effects);
    }

    /**
     * Verifica si la figura tiene aplicado el efecto parametrizado.
     *
     * @param effect    Efecto parametrizado.
     * @return          true si el efecto está aplicado; false sino.
     */
    public boolean hasEffect(EffectType effect){
        return effects.contains(effect);
    }

    /**
     * Agrega el efecto parametrizado a la figura. Si el efecto esta
     * activado no realiza cambios.
     *
     * @param eff       Efecto parametrizado a agregar.
     */
    public void addEffect(EffectType eff){
        effects.add(eff);
    }

    /**
     * Remueve el efecto parametrizado de la figura. Si el efecto no esta
     * activado no realiza cambios.
     *
     * @param eff       Efecto parametrizado a eliminar.
     */
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
    public void draw(DrawingContext dc, boolean isSelected){
        fig.draw(dc, isSelected);
        for(EffectType eff : effects){
            eff.apply(dc, fig);
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

    /**
     * Divide la figura horizontalmente en n partes y devuelve una
     * coleccion con las figuras divididas.
     *
     * @param n     Número de divisiones horizontales deseadas.
     * @return      Lista de CanvasFigure divididas.
     */
    public List<CanvasFigure> widthDivide(int n){
        return wrapFigures(fig.widthDivide(n));
    }

    /**
     * Divide la figura verticalmente en n partes y devuelve devuelve una
     * coleccion con las figuras divididas.
     *
     * @param n     Número de divisiones verticales deseadas.
     * @return      Lista de CanvasFigure divididas.
     */
    public List<CanvasFigure> heightDivide(int n){
        return wrapFigures(fig.heightDivide(n));
    }

    /**
     * Crea una copia de esta figura manteniendo los mismos efectos activados.
     *
     * @return      Nuevo CanvasFigure que representa una copia de esta instancia.
     */
    public CanvasFigure copy(){
        Figure copiedFig = fig.clone();
        return new CanvasFigure(copiedFig, effects);
    }

    @Override
    public String toString(){
        return fig.toString();
    }
}