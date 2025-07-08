package src.frontend;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import src.backend.CanvasFigure;
import src.backend.CanvasState;
import src.backend.model.*;
import src.backend.model.Border;
import src.backend.model.effects.EffectType;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import java.util.*;

public class PaintPane extends BorderPane{

	private final CanvasState canvasState;
	private final Canvas canvas = new Canvas(800, 600);
	private final GraphicsContext gc = canvas.getGraphicsContext2D();
	private final JavaFXDrawingContext dc = new JavaFXDrawingContext(gc);

	private final ToggleButton selectionButton = new ToggleButton("Seleccionar");
	private final ToggleButton rectangleButton = new ToggleButton("Rectángulo");
	private final ToggleButton circleButton = new ToggleButton("Círculo");
	private final ToggleButton squareButton = new ToggleButton("Cuadrado");
	private final ToggleButton ellipseButton = new ToggleButton("Elipse");
	private final ToggleButton deleteButton = new ToggleButton("Borrar");
	private final ChoiceBox<Border> borderStyleChooser = new ChoiceBox<>();
	private final Button copyFormatButton = new Button("Copiar Fmt.");
	private final Button pasteFormatButton = new Button("Pegar Fmt.");

	private final ToggleButton divideByLengthButton = new ToggleButton("Dividir An.");
	private final ToggleButton divideByHeightButton = new ToggleButton("Dividir Al.");
	private final ToggleButton multiplyButton = new ToggleButton("Multiplicar");
	private final ToggleButton moveToButton = new ToggleButton("Trasladar");

	private final Label effectsLabel = createTitleLabel("Efectos:");
	private final CheckBox lightenCheckbox = new CheckBox("Aclaramiento");
	private final CheckBox darkenCheckbox = new CheckBox("Oscurecimiento");
	private final CheckBox mirrorHCheckbox = new CheckBox("Espejo Horizontal");
	private final CheckBox mirrorVCheckbox = new CheckBox("Espejo Vertical");
	private final HBox effectsRow = buildCheckboxRow(lightenCheckbox, darkenCheckbox, mirrorHCheckbox, mirrorVCheckbox);

	private final Map<CheckBox, EffectType> effectMap = new HashMap<>();
	private final Map<ToggleButton, FigureFactory> figureFactories = new HashMap<>();

	private void initializeFigureFactories(){
		figureFactories.put(rectangleButton, Rectangle::new);
		figureFactories.put(circleButton, Circle::new);
		figureFactories.put(squareButton, Square::new);
		figureFactories.put(ellipseButton, Ellipse::new);
	}

	private void initializeEffectMap(){
		effectMap.put(lightenCheckbox, EffectType.LIGHTENED);
		effectMap.put(darkenCheckbox, EffectType.DARKENED);
		effectMap.put(mirrorHCheckbox, EffectType.HORIZONTAL_MIRROR);
		effectMap.put(mirrorVCheckbox, EffectType.VERTICAL_MIRROR);
	}

	private final ColorPicker fillColorPicker = new ColorPicker(javafx.scene.paint.Color.YELLOW);

	private Point startPoint;
	private CanvasFigure selectedFigure;
	private final StatusPane statusPane;

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

	private void initToggleButtons(ToggleButton[] buttons, ToggleGroup group){
		for (ToggleButton button : buttons) {
			button.setMinWidth(90);
			button.setPrefWidth(90);
			button.setToggleGroup(group);
			button.setCursor(Cursor.HAND);
		}
	}

	private VBox buildVBox(Node... nodes){
		VBox box = new VBox(10);
		box.setPadding(new Insets(5));
		box.setStyle("-fx-background-color: #CCCCCC");
		box.setPrefWidth(120);
		box.setFillWidth(false);
		box.setMinHeight(300);
		for (Node node : nodes) {
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

		initializeEffectMap();
		initializeFigureFactories();

		ToggleButton[] toolsLeft = {selectionButton, rectangleButton, circleButton, squareButton, ellipseButton, deleteButton};
		ToggleButton[] toolsRight = {divideByLengthButton, divideByHeightButton, multiplyButton, moveToButton};

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

		VBox buttonsBoxLeft = buildVBox(selectionButton,
				new VBox(rectangleButton, circleButton, squareButton, ellipseButton, deleteButton),
				borderStyleChooser, fillColorPicker, copyFormatButton, pasteFormatButton);

		VBox buttonsBoxRight = buildVBox(createTitleLabel("Operaciones:"), new VBox(divideByLengthButton, divideByHeightButton),
				multiplyButton, moveToButton);

		HBox buttonsTopBox = buildHBox(effectsLabel, effectsRow);

		canvas.setOnMousePressed(this::onMousePressed);
		canvas.setOnMouseReleased(this::onMouseRelease);
		canvas.setOnMouseMoved(this::onMouseMoved);
		canvas.setOnMouseDragged(this::onMouseDragged);
		canvas.setOnMouseClicked(this::onMouseClicked);

		deleteButton.setOnAction(event -> onDeleteButton());
		copyFormatButton.setOnAction(event -> onCopyFormatButton());
		pasteFormatButton.setOnAction(event -> onPasteFormatButton());
		borderStyleChooser.setOnAction(event -> onChangeFigureProperty());
		fillColorPicker.setOnAction(event -> onChangeFigureProperty());

		lightenCheckbox.setOnAction(event -> onEffectChanged());
		darkenCheckbox.setOnAction(event -> onEffectChanged());
		mirrorHCheckbox.setOnAction(event -> onEffectChanged());
		mirrorVCheckbox.setOnAction(event -> onEffectChanged());

		divideByLengthButton.setOnAction(event -> onDivideWidth());
		divideByHeightButton.setOnAction(event -> onDivideHeight());
		multiplyButton.setOnAction(event -> onMultiply());
		moveToButton.setOnAction(event -> onMoveTo());

		setLeft(buttonsBoxLeft);
		setRight(buttonsBoxRight);
		buttonsTopBox.setAlignment(Pos.CENTER);
		setTop(buttonsTopBox);
		setBottom(statusPane);
		StackPane canvasWrapper = new StackPane(canvas);
		canvasWrapper.setMinSize(0, 0);
		statusPane.setMinHeight(30);
		setMinSize(600, 400);

		canvas.widthProperty().bind(canvasWrapper.widthProperty());
		canvas.heightProperty().bind(canvasWrapper.heightProperty());
		canvas.widthProperty().addListener(evt -> redrawCanvas());
		canvas.heightProperty().addListener(evt -> redrawCanvas());
		setCenter(canvasWrapper);
	}

	private void errorDialog(String s){
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("¡ERROR!");
		alert.setHeaderText(null);
		alert.setContentText(s);
		alert.showAndWait();
	}

	private boolean checkForSelectedFigure(){
		if(selectedFigure == null){
			statusPane.updateStatus("Seleccione una figura...");
			return false;
		}
		return true;
	}

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
				if(n <= 0){
					throw new NumberFormatException("El número debe ser un entero positivo...");
				}
				canvasState.widthDivide(selectedFigure, n);
				selectedFigure = null;
				redrawCanvas();
			}catch(NumberFormatException e){
				errorDialog("¡Ingrese un número entero positivo!");
				statusPane.updateStatus("Entrada inválida.");
			}
		});
	}

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
				if(n <= 0){
					throw new NumberFormatException("El número debe ser un entero positivo...");
				}
				canvasState.heightDivide(selectedFigure, n);
				selectedFigure = null;
				redrawCanvas();
			}catch(NumberFormatException e){
				errorDialog("¡Ingrese un número entero positivo!");
				statusPane.updateStatus("Entrada inválida.");
			}
		});
	}

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
				if(n <= 1){
					throw new NumberFormatException("El número debe ser mayor a 1...");
				}
				canvasState.multiply(selectedFigure, n);
				redrawCanvas();
			}catch(NumberFormatException e){
				errorDialog("¡Ingrese un número entero positivo!");
				statusPane.updateStatus("Entrada inválida.");
			}
		});
	}

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
				redrawCanvas();
			}catch(NumberFormatException e){
				errorDialog("¡Ingrese coordenadas [X, Y]!");
				statusPane.updateStatus("Entrada inválida.");
			}
		});
	}

	private void updateEffectCheckboxes(CanvasFigure figure){
		for(Map.Entry<CheckBox, EffectType> entry : effectMap.entrySet()){
			entry.getKey().setSelected(figure.hasEffect(entry.getValue()));
		}
	}

	private void onEffectChanged(){
		if(selectedFigure != null){
			for(Map.Entry<CheckBox, EffectType> entry : effectMap.entrySet()){
				if(entry.getKey().isSelected()){
					selectedFigure.addEffect(entry.getValue());
				}else{
					selectedFigure.removeEffect(entry.getValue());
				}
			}
			redrawCanvas();
		}
	}

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
				fillColorPicker.setValue(new javafx.scene.paint.Color(selectedFigure.getFillColor().getRed() / 255.0, selectedFigure.getFillColor().getGreen() / 255.0,
						selectedFigure.getFillColor().getBlue() / 255.0, selectedFigure.getFillColor().getOpacity()));
				borderStyleChooser.setValue(selectedFigure.getBorder());
			}else{
				selectedFigure = null;
				statusPane.updateStatus("¡Ninguna figura encontrada!");
			}
			redrawCanvas();
		}
	}

	private void onMousePressed(MouseEvent event){
		startPoint = new Point(event.getX(), event.getY());
		if(selectionButton.isSelected()){
			List<CanvasFigure> figuresList = new ArrayList<>();
			for(CanvasFigure fig : canvasState){
				figuresList.add(fig);
			}

			CanvasFigure figureUnderMouse = null;
			for(int i = figuresList.size() - 1; i >= 0; i--){
				CanvasFigure figure = figuresList.get(i);
				if(figure.contains(startPoint)){
					figureUnderMouse = figure;
					break;
				}
			}

			if(figureUnderMouse != null){
				selectedFigure = figureUnderMouse;
				statusPane.updateStatus("Arrastrando: " + selectedFigure);
				updateEffectCheckboxes(selectedFigure);
				redrawCanvas();
			}else{
				if(selectedFigure != null){
					statusPane.updateStatus("Arrastrando: " + selectedFigure);
				}
			}
		}
	}

	private void onMouseRelease(MouseEvent event){
		Point endPoint = new Point(event.getX(), event.getY());
		if(selectionButton.isSelected() || startPoint == null){
			return;
		}

		Figure newFigure = null;
		Color actualColor = new Color((int) (fillColorPicker.getValue().getRed() * 255), (int) (fillColorPicker.getValue().getGreen() * 255),
				(int) (fillColorPicker.getValue().getBlue() * 255), fillColorPicker.getValue().getOpacity());
		Border actualBorderStyle = borderStyleChooser.getValue();
		boolean found = false;
		Iterator<Map.Entry<ToggleButton, FigureFactory>> it = figureFactories.entrySet().iterator();

		while(!found && it.hasNext()){
			Map.Entry<ToggleButton, FigureFactory> aux = it.next();
			if(aux.getKey().isSelected()){
				newFigure = aux.getValue().create(startPoint, endPoint, actualColor, actualBorderStyle);
				found = true;
			}
		}

		if(newFigure != null){
			canvasState.addFigure(newFigure, lightenCheckbox.isSelected(), darkenCheckbox.isSelected(),
					mirrorHCheckbox.isSelected(), mirrorVCheckbox.isSelected());
		}
		startPoint = null;
		redrawCanvas();
	}

	private void onMouseMoved(MouseEvent event){
		Point eventPoint = new Point(event.getX(), event.getY());
		CanvasFigure topFigure = canvasState.selectFigureAtPoint(eventPoint);
		statusPane.updateStatus(topFigure == null ? eventPoint.toString() : topFigure.toString());
	}

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
			redrawCanvas();
			selectionButton.setSelected(true);
			statusPane.updateStatus("Figura eliminada.");
		}
	}

	private void onCopyFormatButton(){
		if(checkForSelectedFigure()){
			FormatClipboard.fillColor = selectedFigure.getFillColor();
			FormatClipboard.borderStyle = selectedFigure.getBorder();
			pasteFormatButton.setDisable(false);
			statusPane.updateStatus("Formato copiado: " + selectedFigure);
		}
	}

	private void onPasteFormatButton(){
		if(checkForSelectedFigure()){
			selectedFigure.setFillColor(FormatClipboard.fillColor);
			selectedFigure.setBorder(FormatClipboard.borderStyle);
			redrawCanvas();
			statusPane.updateStatus("Formato pegado en: " + selectedFigure);
		}
	}

	private void onChangeFigureProperty(){
		if(selectedFigure != null){
			selectedFigure.setFillColor(new Color((int) (fillColorPicker.getValue().getRed() * 255), (int) (fillColorPicker.getValue().getGreen() * 255),
					(int) (fillColorPicker.getValue().getBlue() * 255), fillColorPicker.getValue().getOpacity()));
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
			figure.draw(dc, isSelected);
		}
	}
}