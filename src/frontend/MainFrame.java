package src.frontend;
import javafx.scene.layout.Priority;
import src.backend.CanvasState;
import javafx.scene.layout.VBox;

public class MainFrame extends VBox{

    public MainFrame(CanvasState canvasState){
//        getChildren().add(new AppMenuBar());
//        StatusPane statusPane = new StatusPane();
//        getChildren().add(new PaintPane(canvasState, statusPane));
//        getChildren().add(statusPane);

    /*Cambie lo de arriba por lo de abajo porque era mejor estilo y permitia que se complete toda la pantalla
    * sin dejar el espacio ese blanco de abajo. */
        AppMenuBar menuBar = new AppMenuBar();
        StatusPane statusPane = new StatusPane();
        PaintPane paintPane = new PaintPane(canvasState, statusPane);

        // El PaintPane va a crecer y ocupar todos el espacio restante.
        VBox.setVgrow(paintPane, Priority.ALWAYS);

        getChildren().addAll(menuBar, paintPane, statusPane);
    }
}