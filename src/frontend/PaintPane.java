package src.frontend;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.shape.StrokeLineCap;
import src.backend.CanvasFigure;
import src.backend.CanvasState;
import src.backend.model.*;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class PaintPane extends BorderPane{

	private final CanvasState canvasState;					/* Estado del canvas */
	private final Canvas canvas = new Canvas(800, 600);
	private final GraphicsContext gc = canvas.getGraphicsContext2D();

	private static final Color DEFAULT_FILL_COLOR = Color.YELLOW;
	private static final Color DEFAULT_BORDER_COLOR = Color.BLACK;
	private static final Color SELECTED_BORDER_COLOR = Color.RED;

	/*  --- Botones Barra Izquierda --- */
	private final ToggleButton selectionButton = new ToggleButton("Seleccionar");
	private final ToggleButton rectangleButton = new ToggleButton("Rectángulo");
	private final ToggleButton circleButton = new ToggleButton("Círculo");
	private final ToggleButton squareButton = new ToggleButton("Cuadrado");
	private final ToggleButton ellipseButton = new ToggleButton("Elipse");
	private final ToggleButton deleteButton = new ToggleButton("Borrar");
	private final ChoiceBox<Border> borderStyleChooser = new ChoiceBox<>();
	private final Button copyFormatButton = new Button("Copiar Fmt.");
	private final Button pasteFormatButton = new Button("Pegar Fmt.");

	/* --- Botones Barra Derecha (Operaciones) --- */
	private final ToggleButton divideByLengthButton = new ToggleButton("Dividir An.");
	private final ToggleButton divideByHeightButton = new ToggleButton("Dividir Al.");
	private final ToggleButton divideButton = new ToggleButton("Dividir");
	private final ToggleButton multiplyButton = new ToggleButton("Multiplicar");
	private final ToggleButton moveToButton = new ToggleButton("Trasladar");

	/* --- Barra Superior (Efectos) --- */
	private final Label effectsLabel = createTitleLabel("Efectos:");
	private final CheckBox lightenCheckbox = new CheckBox("Aclaramiento");
	private final CheckBox darkenCheckbox = new CheckBox("Oscurecimiento");
	private final CheckBox mirrorHCheckbox = new CheckBox("Espejo Horizontal");
	private final CheckBox mirrorVCheckbox = new CheckBox("Espejo Vertical");
	private final HBox effectsRow = buildCheckboxRow(lightenCheckbox, darkenCheckbox, mirrorHCheckbox, mirrorVCheckbox);

	/* Selector de color de relleno */
	private final ColorPicker fillColorPicker = new ColorPicker(Color.YELLOW);

	/* Dibujar una figura */
	private Point startPoint;

	/* Seleccionar una figura */
	private CanvasFigure selectedFigure;

	/* Barra de estado */
	private final StatusPane statusPane;

	/*
	 * Portapapeles para guardar el formato copiado de la figura.
	 */
	private static class FormatClipboard{
		static Color fillColor;
		static Border borderStyle;
	}

	private HBox buildCheckboxRow(CheckBox... boxes){
		HBox box = new HBox(10);
		box.setPadding(new Insets(5));
		box.getChildren().addAll(boxes);
		return box;
	}

	private HBox buildHBox(Node... nodes){
		HBox topMenu = new HBox(10, effectsLabel, effectsRow);
		topMenu.setPadding(new Insets(5));
		topMenu.setStyle("-fx-background-color: #EEEEEE");
		return topMenu;
	}

	/*
	 * Metodo que utiliza un build para crear ambas cajas izquierda y derecha,
	 * ademas un metodo auxiliar para implementar un titulo, el cual se puede reutilizar.
	 * Lo usamos para hacer el label operaciones.
	*/
	private void initToggleButtons(ToggleButton[] buttons, ToggleGroup group){
		for(ToggleButton button : buttons){
			button.setMinWidth(90);
			button.setToggleGroup(group);
			button.setCursor(Cursor.HAND);
		}
	}

	private VBox buildVBox(Node... nodes){
		VBox box = new VBox(10);
		box.setPadding(new Insets(5));
		box.setStyle("-fx-background-color: #CCCCCC");
		box.setPrefWidth(120);
		for(Node node : nodes){
			box.getChildren().add(node);
			box.getChildren().add(new Separator());
		}
		return box;
	}

	private Label createTitleLabel(String text){
		Label label = new Label(text);
		label.setStyle("-fx-font-weight: bold; -fx-padding: 0 0 5 0;");
		return label;
	}

	public PaintPane(CanvasState canvasState, StatusPane statusPane){

		this.canvasState = canvasState;
		this.statusPane = statusPane;

		ToggleButton[] toolsLeft = {selectionButton, rectangleButton, circleButton, squareButton, ellipseButton, deleteButton};
		ToggleButton[] toolsRight = {divideByLengthButton, divideByHeightButton, divideButton, multiplyButton, moveToButton};

		initToggleButtons(toolsLeft, new ToggleGroup());
		initToggleButtons(toolsRight, new ToggleGroup());

		selectionButton.setSelected(true);

		borderStyleChooser.getItems().addAll(Border.values());
		borderStyleChooser.setValue(Border.NORMAL);
		borderStyleChooser.setMinWidth(90);

		copyFormatButton.setMinWidth(90);
		pasteFormatButton.setMinWidth(90);
		pasteFormatButton.setDisable(true);

		fillColorPicker.setMinWidth(90);

		VBox buttonsBoxLeft = buildVBox(
				selectionButton,
				new VBox(rectangleButton, circleButton, squareButton, ellipseButton, deleteButton),
				borderStyleChooser,
				fillColorPicker,
				copyFormatButton,
				pasteFormatButton
		);

		VBox buttonsBoxRight = buildVBox(
				createTitleLabel("Operaciones:"),
				new VBox(divideByLengthButton, divideByHeightButton, divideButton),
				multiplyButton,
				moveToButton
		);

		HBox buttonsTopBox = buildHBox(
				effectsLabel,
				effectsRow
		);

		canvas.setOnMousePressed(this::onMousePressed);
		canvas.setOnMouseReleased(this::onMouseRelease);
		canvas.setOnMouseMoved(this::onMouseMoved);
		canvas.setOnMouseDragged(this::onMouseDragged);

		deleteButton.setOnAction(event -> onDeleteButton());
		copyFormatButton.setOnAction(event -> onCopyFormatButton());
		pasteFormatButton.setOnAction(event -> onPasteFormatButton());
		borderStyleChooser.setOnAction(event -> onChangeFigureProperty());
		de
		setLeft(buttonsBoxLeft);
		setRight(buttonsBoxRight);
		buttonsTopBox.setAlignment(Pos.CENTER);
		setTop(buttonsTopBox);
		setBottom(statusPane);
		Pane canvasWrapper = new Pane(canvas);
		canvas.widthProperty().bind(canvasWrapper.widthProperty());
		canvas.heightProperty().bind(canvasWrapper.heightProperty());
		setCenter(canvasWrapper);
	}

	/*
	 * Se ejecuta al hacer click sin arrastrar sobre el lienzo.
	 * Si está activada la herramienta de selección (selectionButton)
	 * busca si el punto clickeado pertenece a alguna figura.
	 */
	private void onMouseClicked(MouseEvent event){
		if(selectionButton.isSelected()){
			Point eventPoint = new Point(event.getX(), event.getY());
			boolean found = false;
			StringBuilder label = new StringBuilder("Se seleccionó: ");
			for(CanvasFigure figure : canvasState){
				if(figureBelongs(figure, eventPoint)){
					found = true;
					selectedFigure = figure;
					label.append(figure);
				}
			}
			if(found){
				statusPane.updateStatus(label.toString());
			} else{
				selectedFigure = null;
				statusPane.updateStatus("Ninguna figura encontrada");
			}
			redrawCanvas();
		}
	}

	/*
	 * Guarda el punto donde el mouse fue presionado. Este punto se usa
	 * para luego calcular la posicion de la figura al soltar el mouse.
	 */
	private void onMousePressed(MouseEvent event){
		startPoint = new Point(event.getX(), event.getY());
	}

	/*
	 * Se ejecuta cuando se suelta el mouse. Según la herramienta activa
	 * crea una nueva figura con startPoint y endPoint. Agrega esa figura al canvasState y la dibuja.
	 */
	private void onMouseRelease(MouseEvent event){
		Point endPoint = new Point(event.getX(), event.getY());
		if(selectionButton.isSelected() || startPoint == null || endPoint.getX() <= startPoint.getX() || endPoint.getY() <= startPoint.getY()){
			return;
		}

		Figure newFigure = null;
		Color actualColor = fillColorPicker.getValue();
		Border actualBorderStyle = borderStyleChooser.getValue();

		if(rectangleButton.isSelected()){
			newFigure = new Rectangle(startPoint, endPoint, actualColor, actualBorderStyle);
		} else if(circleButton.isSelected()){
			double circleRadius = Math.abs(endPoint.getX() - startPoint.getX());
			newFigure = new Circle(startPoint, circleRadius, actualColor, actualBorderStyle);
		} else if(squareButton.isSelected()){
			double size = Math.abs(endPoint.getX() - startPoint.getX());
			newFigure = new Square(startPoint, size, actualColor, actualBorderStyle);
		} else if(ellipseButton.isSelected()){
			Point centerPoint = new Point(Math.abs(endPoint.getX() + startPoint.getX()) / 2, (Math.abs((endPoint.getY() + startPoint.getY())) / 2));
			double sMayorAxis = Math.abs(endPoint.getX() - startPoint.getX());
			double sMinorAxis = Math.abs(endPoint.getY() - startPoint.getY());
			newFigure = new Ellipse(centerPoint, sMayorAxis, sMinorAxis, actualColor, actualBorderStyle);
		}
		if(newFigure != null){
			canvasState.addFigure(newFigure);
		}

		startPoint = null;
		redrawCanvas();
	}

	/*
	 * Se muestra la informacion de la figura si el cursor pasa por encima de alguna
	 * dentro del lienzo.
	 */
	private void onMouseMoved(MouseEvent event){
		Point eventPoint = new Point(event.getX(), event.getY());
		CanvasFigure topFigure = canvasState.selectFigureAtPoint(eventPoint);
		statusPane.updateStatus(topFigure == null ? eventPoint.toString() : topFigure.toString());
	}

	/*
	 * Calcula cuánto se movió el mouse desde startPoint.
	 * Mueve la figura seleccionada y redibuja el lienzo.
	 */
	private void onMouseDragged(MouseEvent event){
		if(selectionButton.isSelected() && selectedFigure != null && startPoint != null){
			Point eventPoint = new Point(event.getX(), event.getY());
			double dx = eventPoint.getX() - startPoint.getX();
			double dy = eventPoint.getY() - startPoint.getY();
			selectedFigure.move(dx, dy);
			startPoint = eventPoint;
			statusPane.updateStatus(selectedFigure.toString());
			redrawCanvas();
		}
	}

	private void onDeleteButton(){
		if(selectedFigure != null){
			canvasState.deleteFigure(selectedFigure);
			selectedFigure = null;
		}
	}

	private void onCopyFormatButton(){
		if(selectedFigure != null){
			FormatClipboard.fillColor = selectedFigure.getFillColor();
			FormatClipboard.borderStyle = selectedFigure.getBorder();
			pasteFormatButton.setDisable(false);
			statusPane.updateStatus("Formato copiado: " + selectedFigure);
		} else {
			statusPane.updateStatus("Seleccione una figura para copiar su formato...");
		}
	}

	private void onPasteFormatButton(){
		if(selectedFigure != null){
			selectedFigure.setFillColor(FormatClipboard.fillColor);
			selectedFigure.setBorder(FormatClipboard.borderStyle);
			redrawCanvas();
			statusPane.updateStatus("Formato pegado en: " + selectedFigure);
		} else {
			statusPane.updateStatus("Seleccione una figura para pegar el formato...");
		}
	}

	private void onChangeFigureProperty(){
		if(selectedFigure != null){
			selectedFigure.setFillColor(fillColorPicker.getValue());
			selectedFigure.setBorder(borderStyleChooser.getValue());
			redrawCanvas();
		}
	}

	private boolean figureBelongs(CanvasFigure figure, Point eventPoint){
		return figure.contains(eventPoint);
	}

	private void redrawCanvas(){
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for(CanvasFigure figure : canvasState){
			boolean isSelected = figure.equals(selectedFigure);
			figure.draw(gc, isSelected);
		}
	}

	private void applyBorderStyle(GraphicsContext gc, Border border){
		if(border == null) border = Border.NORMAL;

		switch(border){
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
}