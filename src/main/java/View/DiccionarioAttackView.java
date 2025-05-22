package View;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import util.DiccionarioAttack;

import java.io.File;

public class DiccionarioAttackView {

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Simulación de Ataque de Diccionario");

        Label hashLabel = new Label("Hash SHA-1:");
        TextField hashField = new TextField();

        Label diccionarioLabel = new Label("Archivo diccionario:");
        TextField diccionarioField = new TextField();
        diccionarioField.setEditable(false);
        Button btnBuscar = new Button("Buscar");
        HBox diccionarioBox = new HBox(10, diccionarioField, btnBuscar);

        TextArea resultadoArea = new TextArea();
        resultadoArea.setEditable(false);
        resultadoArea.setWrapText(true);
        resultadoArea.setPrefHeight(200);

        Button btnIniciar = new Button("Iniciar Ataque");

        VBox root = new VBox(10,
                hashLabel, hashField,
                diccionarioLabel, diccionarioBox,
                btnIniciar, resultadoArea
        );
        root.setPadding(new Insets(15));

        btnBuscar.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleccionar diccionario");
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                diccionarioField.setText(file.getAbsolutePath());
            }
        });

        btnIniciar.setOnAction(e -> {
            String hash = hashField.getText().trim();
            String ruta = diccionarioField.getText().trim();
            if (hash.isEmpty() || ruta.isEmpty()) {
                resultadoArea.setText("⚠️ Completa todos los campos.");
                return;
            }
            String resultado = DiccionarioAttack.ataqueDiccionario(hash, ruta);
            resultadoArea.setText(resultado);
        });

        Scene scene = new Scene(root, 500, 400);
        stage.setScene(scene);
        stage.show();
    }
}
