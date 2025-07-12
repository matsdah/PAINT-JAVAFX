package src.frontend;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import src.backend.CanvasFigure;
import src.backend.CanvasState;
import src.backend.model.*;

import java.util.HashMap;
import java.util.Map;

public class FigureToolBar extends Bar{

    private final StatusPane statusPane;
    private CanvasFigure selectedFigure;
    private final CanvasState canvasState;

    private final ToggleButton selectionButton = new ToggleButton("Seleccionar");
    private final ToggleButton rectangleButton = new ToggleButton("Rectángulo");
    private final ToggleButton circleButton = new ToggleButton("Círculo");
    private final ToggleButton squareButton = new ToggleButton("Cuadrado");
    private final ToggleButton ellipseButton = new ToggleButton("Elipse");
    private final ToggleButton deleteButton = new ToggleButton("Borrar");
    private final ChoiceBox<Border> borderStyleChooser = new ChoiceBox<>();
    private final Button copyFormatButton = new Button("Copiar Fmt.");
    private final Button pasteFormatButton = new Button("Pegar Fmt.");

    private final Map<ToggleButton, FigureFactory> figureFactories = new HashMap<>();

    private void initializeFigureFactories(){
        figureFactories.put(rectangleButton, Rectangle::new);
        figureFactories.put(circleButton, Circle::new);
        figureFactories.put(squareButton, Square::new);
        figureFactories.put(ellipseButton, Ellipse::new);
    }

    ToggleButton[] toolsLeft = {selectionButton, rectangleButton, circleButton, squareButton, ellipseButton, deleteButton};

    private final ColorPicker fillColorPicker = new ColorPicker(javafx.scene.paint.Color.YELLOW);

    VBox buttonsBoxLeft = buildVBox(selectionButton,
            new VBox(rectangleButton, circleButton, squareButton, ellipseButton, deleteButton),
            borderStyleChooser, fillColorPicker, copyFormatButton, pasteFormatButton);

    public FigureToolBar(PaintPane paintPane){
        super(paintPane);
        this.statusPane = paintPane.getStatusPane();
        this.selectedFigure = paintPane.getSelectedFigure();
        this.canvasState = paintPane.getCanvasState();
        initToggleButtons(toolsLeft,new ToggleGroup());
        initializeFigureFactories();
        selectionButton.setSelected(true);
        deleteButton.setOnAction(event -> onDeleteButton());
        copyFormatButton.setOnAction(event -> onCopyFormatButton());
        pasteFormatButton.setOnAction(event -> onPasteFormatButton());
        borderStyleChooser.setOnAction(event -> onChangeFigureProperty());
        fillColorPicker.setOnAction(event -> onChangeFigureProperty());
    }

    /**
     * Copia el color de relleno y estilo de borde de la figura seleccionada.
     */
    private void onCopyFormatButton(){
        if(checkForSelectedFigure()){
            FormatClipboard.fillColor = selectedFigure.getFillColor();
            FormatClipboard.borderStyle = selectedFigure.getBorder();
            pasteFormatButton.setDisable(false);
            statusPane.updateStatus("Formato copiado: " + selectedFigure);
        }
    }

    /**
     * Pega el color de relleno y estilo de borde a la figura seleccionada.
     */
    private void onPasteFormatButton(){
        if(checkForSelectedFigure()){
            selectedFigure.setFillColor(FormatClipboard.fillColor);
            selectedFigure.setBorder(FormatClipboard.borderStyle);
            paintPane.redrawCanvas();
            statusPane.updateStatus("Formato pegado en: " + selectedFigure);
        }
    }

    private void onChangeFigureProperty(){
        if(selectedFigure != null){
            selectedFigure.setFillColor(new Color((int) (fillColorPicker.getValue().getRed() * 255), (int) (fillColorPicker.getValue().getGreen() * 255),
                    (int) (fillColorPicker.getValue().getBlue() * 255), fillColorPicker.getValue().getOpacity()));
            selectedFigure.setBorder(borderStyleChooser.getValue());
            paintPane.redrawCanvas();
        }
    }

    /**
     * Elimina la figura seleccionada del lienzo.
     */
    private void onDeleteButton(){
        if(selectedFigure != null){
            canvasState.deleteFigure(selectedFigure);
            selectedFigure = null;
            paintPane.redrawCanvas();
            selectionButton.setSelected(true);
            statusPane.updateStatus("Figura eliminada.");
        }
    }

    public VBox getVBox(){
        return buttonsBoxLeft;
    }

    private static class FormatClipboard{
        static Color fillColor;
        static Border borderStyle;
    }
}
