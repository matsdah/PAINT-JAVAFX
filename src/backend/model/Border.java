package src.backend.model;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.StrokeLineCap;

public enum Border{
    NORMAL("Normal"){
        @Override
        public void apply(GraphicsContext gc){
            gc.setLineWidth(1);
        }
    },

    PIXELEADO("Pixelado"){
        @Override
        public void apply(GraphicsContext gc){
            gc.setLineWidth(5);
            gc.setLineCap(StrokeLineCap.BUTT);
            gc.setLineDashes(1, 1);
        }
    },

    PUNTEADO_FINO("Punteado Fino"){
        @Override
        public void apply(GraphicsContext gc){
            gc.setLineWidth(1);
            gc.setLineCap(StrokeLineCap.ROUND);
            gc.setLineDashes(2, 6);
        }
    },

    PUNTEADO_COMPLEJO("Punteado Complejo"){
        @Override
        public void apply(GraphicsContext gc){
            gc.setLineWidth(3);
            gc.setLineCap(StrokeLineCap.SQUARE);
            gc.setLineDashes(25, 10, 15, 10);
        }
    };

    private final String borderName;

    Border(String displayName) {
        this.borderName = displayName;
    }

    public abstract void apply(GraphicsContext gc);

    @Override
    public String toString() {
        return borderName;
    }
}
