package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainView {
    private Stage primaryStage;

    public void show() {
        primaryStage = new Stage();
        primaryStage.setTitle("Sistema de Seguridad - Menú Principal");

        // Panel principal
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #f0f2f5;");

        // Barra superior
        HBox topBar = new HBox();
        topBar.setStyle("-fx-background-color: #2c3e50; -fx-padding: 15;");
        Label title = new Label("Sistema de Seguridad");
        title.setFont(Font.font("Arial", 20));
        title.setTextFill(Color.WHITE);
        topBar.getChildren().add(title);
        root.setTop(topBar);

        // Panel central con opciones
        VBox centerBox = new VBox(20);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setPadding(new Insets(30));

        Label welcomeLabel = new Label("Bienvenido al sistema");
        welcomeLabel.setFont(Font.font(18));

        Button btnDiccionario = createMenuButton("Simular Ataque de Diccionario");
        btnDiccionario.setOnAction(e -> new DiccionarioAttackView().show());

        Button btnSalir = createMenuButton("Salir del sistema");
        btnSalir.setStyle("-fx-background-color: #e74c3c;");
        btnSalir.setOnAction(e -> primaryStage.close());

        centerBox.getChildren().addAll(welcomeLabel, btnDiccionario, btnSalir);
        root.setCenter(centerBox);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createMenuButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 14px;");
        button.setPrefWidth(250);
        button.setPrefHeight(40);
        return button;
    }
}