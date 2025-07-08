package src.frontend;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.StrokeLineCap;
import src.backend.DrawingContext;
import src.backend.model.Color;
import src.backend.model.LineCap;
import java.util.Map;

public class JavaFXDrawingContext implements DrawingContext{
    private final GraphicsContext gc;
    private Map<LineCap, StrokeLineCap> lineCapMap;

    public JavaFXDrawingContext(GraphicsContext gc){
        this.gc = gc;
        setLineCapMap();
    }

    private void setLineCapMap(){
        lineCapMap = Map.of(LineCap.BUTT, StrokeLineCap.BUTT, LineCap.ROUND, StrokeLineCap.ROUND, LineCap.SQUARE, StrokeLineCap.SQUARE);
    }

    @Override
    public void setFill(Color color){
        gc.setFill(new javafx.scene.paint.Color(color.getRed() / 255.0, color.getGreen() / 255.0, color.getBlue() / 255.0, color.getOpacity()));
    }

    @Override
    public void setStroke(Color color){
        gc.setStroke(new javafx.scene.paint.Color(color.getRed() / 255.0, color.getGreen() / 255.0, color.getBlue() / 255.0, color.getOpacity()));
    }

    @Override
    public void setLineWidth(double width){
        gc.setLineWidth(width);
    }

    @Override
    public void setLineCap(LineCap cap){
        gc.setLineCap(lineCapMap.get(cap));
    }

    @Override
    public void setLineDashes(double... dashes){
        gc.setLineDashes(dashes);
    }

    @Override
    public void fillRect(double x, double y, double width, double height){
        gc.fillRect(x, y, width, height);
    }

    @Override
    public void strokeRect(double x, double y, double width, double height){
        gc.strokeRect(x, y, width, height);
    }

    @Override
    public void fillOval(double x, double y, double width, double height){
        gc.fillOval(x, y, width, height);
    }

    @Override
    public void strokeOval(double x, double y, double width, double height){
        gc.strokeOval(x, y, width, height);
    }

    @Override
    public void save(){
        gc.save();
    }

    @Override
    public void restore(){
        gc.restore();
    }
}