package View;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainView {

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Ventana Principal");

        // Crear el contenido de la ventana principal
        StackPane root = new StackPane();
        root.getChildren().add(new Label("Bienvenido al sistema"));

        // Configurar la escena y mostrar la ventana
        Scene scene = new Scene(root, 300, 250);
        stage.setScene(scene);
        stage.show();
    }
}
