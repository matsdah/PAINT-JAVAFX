package src.backend.model.effects;
import src.backend.DrawingContext;
import src.backend.model.Border;
import src.backend.model.Figure;
import src.backend.model.Color;

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

    public abstract void apply(DrawingContext dc, Figure fig);
}