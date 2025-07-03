package src.backend.model;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.StrokeLineCap;

public enum Border{
    NORMAL("Normal"),
    PIXELEADO("Pixelado"),
    PUNTEADO_FINO("Punteado Fino"),
    PUNTEADO_COMPLEJO("Punteado Complejo");

    private final String borderName;

    Border(String displayName) {
        this.borderName = displayName;
    }

    void apply(GraphicsContext gc){
        switch(this){
            case PIXELEADO:
                gc.setLineWidth(5);
                gc.setLineCap(StrokeLineCap.BUTT);
                gc.setLineDashes(1, 1);
                break;
            case PUNTEADO_FINO:
                gc.setLineWidth(1);
                gc.setLineCap(StrokeLineCap.ROUND);
                gc.setLineDashes(2, 6);
                break;
            case PUNTEADO_COMPLEJO:
                gc.setLineWidth(3);
                gc.setLineCap(StrokeLineCap.SQUARE);
                gc.setLineDashes(25, 10, 15, 10);
                break;
            case NORMAL:
            default:
                gc.setLineWidth(1);
                gc.setLineDashes(null);
                break;
        }
    }

    @Override
    public String toString() {
        return borderName;
    }
}
