package src.backend.model.effects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import src.backend.model.Figure;

public enum EffectType {
    LIGHTENED("Aclaramiento"){
        @Override
        public void apply(GraphicsContext gc, Figure fig){
            gc.save();
            gc.setFill(Color.rgb(255, 255, 255, 0.7));
            fig.drawFill(gc);
            gc.restore();
        }
    }, DARKENED("Oscurecimiento"){
        @Override
        public void apply(GraphicsContext gc, Figure fig){
            gc.save();
            gc.setFill(Color.rgb(0, 0, 0, 0.3));
            fig.drawFill(gc);
            gc.restore();
        }
    }, VERTICAL_MIRROR("Espejo Vertical"){
        @Override
        public void apply(GraphicsContext gc, Figure fig) {
            fig.createVerticalMirror().draw(gc, false);
        }
    }, HORIZONTAL_MIRROR("Espejo Horizontal"){
        @Override
        public void apply(GraphicsContext gc, Figure fig){
            fig.createHorizontalMirror().draw(gc, false);
        }
    };

    private EffectType(String effName){
        this.EffectName = effName;
    }

    public final String EffectName;
    public abstract void apply(GraphicsContext gc, Figure fig);
}
