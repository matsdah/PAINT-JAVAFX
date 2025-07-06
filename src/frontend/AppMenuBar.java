package src.frontend;
import javafx.application.Platform;
import javafx.scene.control.*;

public class AppMenuBar extends MenuBar{
    public AppMenuBar() {
        initFileMenu();
        initHelpMenu();
    }

    private void initFileMenu() {
        Menu fileMenu = new Menu("Archivo");
        MenuItem exitItem = new MenuItem("Salir");
        exitItem.setOnAction(e -> confirmExit());
        fileMenu.getItems().add(exitItem);
        getMenus().add(fileMenu);
    }

    private void initHelpMenu() {
        Menu helpMenu = new Menu("Ayuda");
        MenuItem aboutItem = new MenuItem("Acerca De");
        aboutItem.setOnAction(e -> showAboutDialog());
        helpMenu.getItems().add(aboutItem);
        getMenus().add(helpMenu);
    }

    private void confirmExit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Salir");
        alert.setHeaderText("Salir de la aplicación");
        alert.setContentText("¿Está seguro que desea salir de la aplicación?");
        alert.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> Platform.exit());
    }

    private void showAboutDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acerca De");
        alert.setHeaderText(null);
        alert.setContentText("Trabajo Final de Programación Orientada a Objetos. Julio 2025");
        alert.showAndWait();
    }
}