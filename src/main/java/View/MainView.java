package View;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import View.DiccionarioAttackView; // importa tu nueva clase

public class MainView {

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Ventana Principal");

        Label label = new Label("Bienvenido al sistema");
        Button btnDiccionario = new Button("Simular Ataque de Diccionario");

        btnDiccionario.setOnAction(e -> {
            new DiccionarioAttackView().show();
        });

        VBox root = new VBox(20, label, btnDiccionario);
        root.setPrefSize(300, 250);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
