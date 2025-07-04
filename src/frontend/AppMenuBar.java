package src.frontend;
import javafx.application.Platform;
import javafx.scene.control.*;

public class AppMenuBar extends MenuBar{

    public AppMenuBar(){
        Menu file = new Menu("Archivo");
        MenuItem exitMenuItem = new MenuItem("Salir");
        exitMenuItem.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Salir");
            alert.setHeaderText("Salir de la aplicación");
            alert.setContentText("¿Está seguro que desea salir de la aplicación?");
            alert.showAndWait().filter(buttonType -> buttonType.equals(ButtonType.OK)).ifPresent(buttonType -> Platform.exit());
        });
        file.getItems().add(exitMenuItem);

        Menu help = new Menu("Ayuda");
        MenuItem aboutMenuItem = new MenuItem("Acerca De");
        aboutMenuItem.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Acerca De");
            alert.setContentText("Trabajo Final de Programacion Orientada a Objetos. Julio 2025");
            alert.showAndWait();
        });
        help.getItems().add(aboutMenuItem);
        getMenus().addAll(file, help);
    }
}