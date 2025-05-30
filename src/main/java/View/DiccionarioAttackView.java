package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import util.DiccionarioAttack;

public class DiccionarioAttackView {
    private TextArea resultadoArea;
    private TextField hashField;
    private ProgressBar progressBar;

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Ataque de Diccionario");

        // Contenedor principal
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f5f5f5;");

        // Panel de entrada
        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);

        // Campo para el hash
        hashField = new TextField();
        hashField.setPromptText("Ingrese el hash SHA-1");
        hashField.setPrefWidth(400);

        // Información del diccionario
        Label infoLabel = new Label("Diccionario preconfigurado:\n" +
                DiccionarioAttack.getRutaDiccionario());
        infoLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #333;");

        inputGrid.add(new Label("Hash objetivo:"), 0, 0);
        inputGrid.add(hashField, 1, 0);
        inputGrid.add(infoLabel, 0, 1, 2, 1);

        // Área de resultados
        resultadoArea = new TextArea();
        resultadoArea.setEditable(false);
        resultadoArea.setWrapText(true);
        resultadoArea.setPrefHeight(250);
        resultadoArea.setStyle("-fx-font-family: monospace;");

        // Barra de progreso
        progressBar = new ProgressBar(0);
        progressBar.setPrefWidth(Double.MAX_VALUE);

        // Botón de inicio
        Button btnIniciar = new Button("Iniciar Ataque");
        btnIniciar.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        btnIniciar.setOnAction(e -> iniciarAtaque());

        HBox buttonBox = new HBox(btnIniciar);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);

        // Ensamblar la interfaz
        root.getChildren().addAll(
                new Label("CONFIGURACIÓN DEL ATAQUE"),
                inputGrid,
                new Separator(),
                new Label("RESULTADOS"),
                resultadoArea,
                progressBar,
                buttonBox
        );

        // Configurar escena
        Scene scene = new Scene(root, 650, 500);
        stage.setScene(scene);
        stage.show();
    }

    private void iniciarAtaque() {
        String hash = hashField.getText().trim();

        // Validaciones
        if (hash.isEmpty()) {
            mostrarError("Debe ingresar un hash SHA-1");
            return;
        }

        if (!hash.matches("[a-fA-F0-9]{40}")) {
            mostrarError("El hash debe tener exactamente 40 caracteres hexadecimales");
            return;
        }

        if (!DiccionarioAttack.archivoExiste()) {
            mostrarError("No se encuentra el archivo de diccionario\n" +
                    "Verifique que existe en:\n" +
                    DiccionarioAttack.getRutaDiccionario());
            return;
        }

        // Limpiar resultados anteriores
        resultadoArea.clear();
        progressBar.setProgress(0);

        // Ejecutar en segundo plano
        new Thread(() -> {
            String resultado = DiccionarioAttack.ataqueDiccionario(hash);

            // Actualizar interfaz
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