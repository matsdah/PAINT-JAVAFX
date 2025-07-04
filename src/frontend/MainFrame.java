package src.frontend;
import javafx.scene.layout.Priority;
import src.backend.CanvasState;
import javafx.scene.layout.VBox;

public class MainFrame extends VBox{

    public MainFrame(CanvasState canvasState){
        AppMenuBar menuBar = new AppMenuBar();
        StatusPane statusPane = new StatusPane();
        PaintPane paintPane = new PaintPane(canvasState, statusPane);
        VBox.setVgrow(paintPane, Priority.ALWAYS);      /* El PaintPane va a crecer y ocupar todos el espacio restante */
        getChildren().addAll(menuBar, paintPane, statusPane);
    }
}