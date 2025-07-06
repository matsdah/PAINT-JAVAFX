package src.backend.model.effects;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import src.backend.model.Border;
import src.backend.model.Figure;

public enum EffectType{
    LIGHTENED("Aclaramiento"){
        @Override
        public void apply(GraphicsContext gc, Figure fig){
            gc.save();
            gc.setFill(Color.rgb(255, 255, 255, 0.7));
            fig.drawFill(gc);
            gc.restore();
        }
    },
    DARKENED("Oscurecimiento"){
        @Override
        public void apply(GraphicsContext gc, Figure fig){
            gc.save();
            gc.setFill(Color.rgb(0, 0, 0, 0.3));
            fig.drawFill(gc);
            gc.restore();
        }
    },
    HORIZONTAL_MIRROR("Espejo Horizontal"){
        @Override
        public void apply(GraphicsContext gc, Figure fig){
            Figure mirror = fig.createHorizontalMirror();
            mirror.setBorder(Border.NORMAL);
            mirror.draw(gc, false);
        }
    },
    VERTICAL_MIRROR("Espejo Vertical"){
        @Override
        public void apply(GraphicsContext gc, Figure fig){
            Figure mirror = fig.createVerticalMirror();
            mirror.setBorder(Border.NORMAL);
            mirror.draw(gc, false);
        }
    };

    private final String effectName;

    EffectType(String effName){
        this.effectName = effName;
    }

    public String getEffectName(){
        return effectName;
    }

    public abstract void apply(GraphicsContext gc, Figure fig);
}