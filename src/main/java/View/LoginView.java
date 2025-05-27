package View;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import controller.LoginController;

public class LoginView extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Inicio de Sesión");

        // Contenedor principal con imagen de fondo
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #f5f5f5;");

        // Panel de login
        VBox loginBox = new VBox(20);
        loginBox.setAlignment(Pos.CENTER);
        loginBox.setPadding(new Insets(30));
        loginBox.setStyle("-fx-background-color: white; -fx-border-radius: 5; -fx-background-radius: 5;");
        loginBox.setMaxWidth(350);

        // Título
        Label titleLabel = new Label("Inicio de Sesión");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setTextFill(Color.web("#333333"));

        // Campos de formulario
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        TextField usernameField = new TextField();
        usernameField.setPromptText("Usuario");
        usernameField.setPrefWidth(200);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Contraseña");

        grid.add(new Label("Usuario:"), 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(new Label("Contraseña:"), 0, 1);
        grid.add(passwordField, 1, 1);

        // Botón de login
        Button loginButton = new Button("Ingresar");
        loginButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        loginButton.setPrefWidth(200);

        loginButton.setOnAction(e -> {
            LoginController loginController = new LoginController(primaryStage);
            loginController.validarLogin(usernameField.getText(), passwordField.getText());
        });

        // Evento para presionar Enter
        passwordField.setOnAction(e -> loginButton.fire());

        // Agregar componentes al panel
        loginBox.getChildren().addAll(titleLabel, grid, loginButton);
        root.getChildren().add(loginBox);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}