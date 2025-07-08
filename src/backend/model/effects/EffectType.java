package src.backend.model.effects;
import src.backend.DrawingContext;
import src.backend.model.Border;
import src.backend.model.Figure;
import src.backend.model.Color;

/**
 * Enum que representa el tipo de efecto de la figura, ya sea aclaramiento,
 * oscurecimiento, espejado horizontal y espejado vertical.
 */
public enum EffectType{
    LIGHTENED(){
        @Override
        public void apply(DrawingContext dc, Figure fig){
            dc.save();
            dc.setFill(new Color(255, 255, 255, 0.7));
            fig.drawFill(dc);
            dc.restore();
        }
    },
    DARKENED(){
        @Override
        public void apply(DrawingContext dc, Figure fig){
            dc.save();
            dc.setFill(new Color(0, 0, 0, 0.3));
            fig.drawFill(dc);
            dc.restore();
        }
    },
    HORIZONTAL_MIRROR(){
        @Override
        public void apply(DrawingContext dc, Figure fig){
            Figure mirror = fig.createHorizontalMirror();
            mirror.setBorder(Border.NORMAL);
            mirror.draw(dc, false);
        }
    },
    VERTICAL_MIRROR(){
        @Override
        public void apply(DrawingContext dc, Figure fig){
            Figure mirror = fig.createVerticalMirror();
            mirror.setBorder(Border.NORMAL);
            mirror.draw(dc, false);
        }
    };

    /**
     * @param dc    El contexto de dibujo donde se aplicará el efecto.
     * @param fig   La figura a la que se le aplicará el efecto.
     */
    public abstract void apply(DrawingContext dc, Figure fig);
}