package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import util.DiccionarioAttack;
import java.io.File;

public class DiccionarioAttackView {
    private TextArea resultadoArea;
    private TextField hashField;
    private TextField diccionarioField;

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Simulación de Ataque de Diccionario");

        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f9f9f9;");

        // Panel de entrada
        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);

        hashField = new TextField();
        hashField.setPromptText("Ingrese el hash SHA-1");
        hashField.setPrefWidth(300);

        diccionarioField = new TextField();
        diccionarioField.setEditable(false);

        Button btnBuscar = new Button("Examinar");
        btnBuscar.setOnAction(e -> seleccionarArchivo());

        HBox fileBox = new HBox(10, diccionarioField, btnBuscar);
        fileBox.setAlignment(Pos.CENTER_LEFT);

        inputGrid.add(new Label("Hash objetivo:"), 0, 0);
        inputGrid.add(hashField, 1, 0);
        inputGrid.add(new Label("Archivo diccionario:"), 0, 1);
        inputGrid.add(fileBox, 1, 1);

        // Área de resultados
        resultadoArea = new TextArea();
        resultadoArea.setEditable(false);
        resultadoArea.setWrapText(true);
        resultadoArea.setPrefHeight(200);

        // Barra de progreso
        ProgressBar progressBar = new ProgressBar(0);
        progressBar.setPrefWidth(Double.MAX_VALUE);

        // Botones
        Button btnIniciar = new Button("Iniciar Ataque");
        btnIniciar.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white;");
        btnIniciar.setOnAction(e -> iniciarAtaque(progressBar));

        HBox buttonBox = new HBox(10, btnIniciar);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);

        root.getChildren().addAll(
                new Label("Configuración del ataque:"),
                inputGrid,
                new Separator(),
                new Label("Resultados:"),
                resultadoArea,
                progressBar,
                buttonBox
        );

        Scene scene = new Scene(root, 600, 450);
        stage.setScene(scene);
        stage.show();
    }

    private void seleccionarArchivo() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar archivo de diccionario");
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            diccionarioField.setText(file.getAbsolutePath());
        }
    }

    private void iniciarAtaque(ProgressBar progressBar) {
        String hash = hashField.getText().trim();
        String ruta = diccionarioField.getText().trim();

        if (hash.isEmpty() || ruta.isEmpty()) {
            mostrarError("Debe completar todos los campos");
            return;
        }

        if (!hash.matches("[a-fA-F0-9]{40}")) {
            mostrarError("El formato del hash SHA-1 no es válido");
            return;
        }

        // Ejecutar en un hilo separado para no bloquear la UI
        new Thread(() -> {
            String resultado = DiccionarioAttack.ataqueDiccionario(hash, ruta);
            javafx.application.Platform.runLater(() -> {
                resultadoArea.setText(resultado);
                progressBar.setProgress(1.0);
            });
        }).start();
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}