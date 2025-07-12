package src.frontend;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import src.backend.model.Point;
import src.backend.CanvasFigure;
import java.util.Optional;
import src.backend.CanvasState;

public class OperationsBar extends Bar{

    private PaintPane paintPane;
    private CanvasFigure selectedFigure;
    private StatusPane statusPane;
    private CanvasState canvasState;

    private final ToggleButton divideByLengthButton = new ToggleButton("Dividir An.");
    private final ToggleButton divideByHeightButton = new ToggleButton("Dividir Al.");
    private final ToggleButton multiplyButton = new ToggleButton("Multiplicar");
    private final ToggleButton moveToButton = new ToggleButton("Trasladar");

    ToggleButton[] toolsRight = {divideByLengthButton, divideByHeightButton, multiplyButton, moveToButton};
    VBox buttonsBoxRight;

    public OperationsBar(PaintPane paintPane){
        super(paintPane);
        this.selectedFigure = paintPane.getSelectedFigure();
        this.statusPane = paintPane.getStatusPane();
        this.canvasState = paintPane.getCanvasState();
        initToggleButtons(toolsRight, new ToggleGroup());
        divideByLengthButton.setOnAction(event -> onDivideWidth());
        divideByHeightButton.setOnAction(event -> onDivideHeight());
        multiplyButton.setOnAction(event -> onMultiply());
        moveToButton.setOnAction(event -> onMoveTo());
        VBox buttonsBoxRight = buildVBox(createTitleLabel("Operaciones:"), new VBox(divideByLengthButton, divideByHeightButton),
                multiplyButton, moveToButton);
    }

    public VBox getVBox(){
        return buttonsBoxRight;
    }

    /**
     * Divide a lo ancho a la figura seleccionada en n partes enteras.
     * Las divisiones mantienen la misma relacion de altura y anchura.
     */
    private void onDivideWidth(){
        if(!checkForSelectedFigure()){
            return;
        }
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Dividir a lo ancho");
        dialog.setHeaderText(null);
        dialog.setContentText("Ingrese cant. de divisiones:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(nStr -> {
            try{
                int n = Integer.parseInt(nStr);
                canvasState.widthDivide(selectedFigure, n);
                selectedFigure = null;
                paintPane.redrawCanvas();
            }catch(IllegalArgumentException e){
                errorDialog(e.getMessage());
                statusPane.updateStatus("Entrada inválida.");
            }
        });
    }

    /**
     * Divide a lo alto a la figura seleccionada en n partes enteras.
     * Las divisiones mantienen la misma relacion de altura y anchura.
     */
    private void onDivideHeight(){
        if(!checkForSelectedFigure()){
            return;
        }
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Dividir a lo alto");
        dialog.setHeaderText(null);
        dialog.setContentText("Ingrese cantidad de divisiones...");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(nStr -> {
            try{
                int n = Integer.parseInt(nStr);
                canvasState.heightDivide(selectedFigure, n);
                selectedFigure = null;
                paintPane.redrawCanvas();
            }catch(IllegalArgumentException e){
                errorDialog(e.getMessage());
                statusPane.updateStatus("Entrada inválida.");
            }
        });
    }

    /**
     * Crea múltiples copias enteras de la figura seleccionada.
     * Las copias se desplazan con un offset para evitar un solapamiento.
     */
    private void onMultiply(){
        if(!checkForSelectedFigure()){
            return;
        }
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Multiplicar figura");
        dialog.setHeaderText(null);
        dialog.setContentText("Ingrese la cantidad de copias...");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(nStr -> {
            try{
                int n = Integer.parseInt(nStr);
                canvasState.multiply(selectedFigure, n);
                paintPane.redrawCanvas();
            }catch(IllegalArgumentException e){
                errorDialog(e.getMessage());
                statusPane.updateStatus("Entrada inválida.");
            }
        });
    }

    /**
     * Traslada la figura seleccionada a las coordenadas [x, y] especificadas.
     */
    private void onMoveTo(){
        if(!checkForSelectedFigure()){
            return;
        }
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Trasladar figura");
        dialog.setHeaderText(null);
        dialog.setContentText("Ingrese las coordenadas [X, Y]...");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(coords -> {
            try{
                String[] parts = coords.split(",");
                if (parts.length != 2) {
                    throw new NumberFormatException();
                }
                double x = Double.parseDouble(parts[0].trim());
                double y = Double.parseDouble(parts[1].trim());
                canvasState.moveTo(selectedFigure, new Point(x, y));
                paintPane.redrawCanvas();
            }catch(NumberFormatException e){
                errorDialog("¡Ingrese coordenadas [X, Y]!");
                statusPane.updateStatus("Entrada inválida.");
            }
        });
    }

    /**
     * Muestra al usuario un diálogo de error con un mensaje parametrizado.
     *
     * @param s		String de error a mostrar al usuario.
     */
    private void errorDialog(String s){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("¡ERROR!");
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.showAndWait();
    }
}
