package src.frontend;

import src.backend.CanvasFigure;
import src.backend.CanvasState;
import src.backend.model.*;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;

public class PaintPane extends BorderPane{

	private final CanvasState canvasState;
	private final Canvas canvas = new Canvas(800, 600);
	private final GraphicsContext gc = canvas.getGraphicsContext2D();

	private static final Color DEFAULT_FILL_COLOR = Color.YELLOW;
	private static final Color DEFAULT_BORDER_COLOR = Color.BLACK;
	private static final Color SELECTED_BORDER_COLOR = Color.RED;

	/* Botones Barra Izquierda */
	private final ToggleButton selectionButton = new ToggleButton("Seleccionar");
	private final ToggleButton rectangleButton = new ToggleButton("Rectángulo");
	private final ToggleButton circleButton = new ToggleButton("Círculo");
	private final ToggleButton squareButton = new ToggleButton("Cuadrado");
	private final ToggleButton ellipseButton = new ToggleButton("Elipse");
	private final ToggleButton deleteButton = new ToggleButton("Borrar");

	/* Selector de color de relleno */
	private final ColorPicker fillColorPicker = new ColorPicker(Color.YELLOW);

	/* Dibujar una figura */
	private Point startPoint;

	/* Seleccionar una figura */
	private CanvasFigure selectedFigure;

	/* StatusBar */
	private final StatusPane statusPane;

	public PaintPane(CanvasState canvasState, StatusPane statusPane){
		this.canvasState = canvasState;
		this.statusPane = statusPane;
		ToggleButton[] toolsArr = {selectionButton, rectangleButton, circleButton, squareButton, ellipseButton, deleteButton};
		ToggleGroup tools = new ToggleGroup();
		for (ToggleButton tool : toolsArr) {
			tool.setMinWidth(90);
			tool.setToggleGroup(tools);
			tool.setCursor(Cursor.HAND);
		}
		VBox buttonsBox = new VBox(10);
		buttonsBox.getChildren().addAll(toolsArr);
		buttonsBox.getChildren().add(fillColorPicker);
		buttonsBox.setPadding(new Insets(5));
		buttonsBox.setStyle("-fx-background-color: #999");
		buttonsBox.setPrefWidth(100);

		canvas.setOnMousePressed(this::onMousePressed);
		canvas.setOnMouseReleased(this::onMouseRelease);
		canvas.setOnMouseMoved(this::onMouseMoved);
		canvas.setOnMouseDragged(this::onMouseDragged);
		canvas.setOnMouseClicked(this::onMouseClicked);

		deleteButton.setOnAction(event -> {
			if (selectedFigure != null){
				canvasState.deleteFigure(selectedFigure);
				selectedFigure = null;
				redrawCanvas();
			}
		});

		setLeft(buttonsBox);
		setRight(canvas);
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
		if(startPoint == null || endPoint.getX() < startPoint.getX() || endPoint.getY() < startPoint.getY()){
			return;
		}
		Figure newFigure = null;
		if(rectangleButton.isSelected()){
			newFigure = new Rectangle(startPoint, endPoint);
		} else if(circleButton.isSelected()){
			double circleRadius = Math.abs(endPoint.getX() - startPoint.getX());
			newFigure = new Circle(startPoint, circleRadius);
		} else if(squareButton.isSelected()){
			double size = Math.abs(endPoint.getX() - startPoint.getX());
			newFigure = new Square(startPoint, size);
		} else if(ellipseButton.isSelected()){
			Point centerPoint = new Point(Math.abs(endPoint.getX() + startPoint.getX()) / 2, (Math.abs((endPoint.getY() + startPoint.getY())) / 2));
			double sMayorAxis = Math.abs(endPoint.getX() - startPoint.getX());
			double sMinorAxis = Math.abs(endPoint.getY() - startPoint.getY());
			newFigure = new Ellipse(centerPoint, sMayorAxis, sMinorAxis);
		}
		canvasState.addFigure(newFigure);
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
		if(selectionButton.isSelected()){
			Point eventPoint = new Point(event.getX(), event.getY());
			double diffX = (eventPoint.getX() - startPoint.getX());
			double diffY = (eventPoint.getY() - startPoint.getY());

			selectedFigure.move(diffX, diffY);
			redrawCanvas();
		}
	}

	private void redrawCanvas(){
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for(CanvasFigure figure : canvasState){
			gc.setStroke(selectedFigure.equals(figure) ? SELECTED_BORDER_COLOR : DEFAULT_BORDER_COLOR);
			gc.setFill(fillColorPicker.getValue());
			figure.draw(gc);
		}
	}

	private boolean figureBelongs(CanvasFigure figure, Point eventPoint){
		return figure.contains(eventPoint);
	}
}