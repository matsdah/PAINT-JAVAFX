package src.frontend;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Bar{
    protected PaintPane paintPane;

    public Bar(PaintPane paintPane){
        this.paintPane = paintPane;
    }

    protected void initToggleButtons(ToggleButton[] buttons, ToggleGroup group) {
        for (ToggleButton button : buttons) {
            button.setMinWidth(90);
            button.setPrefWidth(90);
            button.setToggleGroup(group);
            button.setCursor(Cursor.HAND);
        }
    }

    protected VBox buildVBox(Node... nodes) {
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

    protected HBox buildCheckboxRow(CheckBox... boxes) {
        HBox box = new HBox(10);
        box.setPadding(new Insets(5));
        box.getChildren().addAll(boxes);
        return box;
    }

    protected boolean checkForSelectedFigure(CanvasFigure selectedFigure){
        return selectedFigure == null;
        if (toReturn) {
            paintPane.getStatusPane().updateStatus("Seleccione una figura...");
            return false;
        }
        return true;
    }

    protected Label createTitleLabel(String text){
        Label label = new Label(text);
        label.setStyle("-fx-font-weight: bold; -fx-padding: 0 0 5 0;");
        return label;
    }

}
