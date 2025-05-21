package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Clase que representa la vista de inicio de sesión del sistema.
 */
public class LoginView {

    /**
     * Muestra la ventana de login.
     * @param stage Stage principal donde se mostrará la escena.
     */
    public void mostrarLogin(Stage stage) {
        Label lblTitulo = new Label("Inicio de Sesión");
        lblTitulo.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        Label lblUsuario = new Label("Usuario:");
        TextField txtUsuario = new TextField();

        Label lblContrasena = new Label("Contraseña:");
        PasswordField txtContrasena = new PasswordField();

        Button btnLogin = new Button("Iniciar Sesión");
        Button btnRegistro = new Button("Registrarse");

        // Layout vertical con espaciado y margen
        VBox vbox = new VBox(12);
        vbox.setPadding(new Insets(25));
        vbox.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(
                lblTitulo,
                lblUsuario, txtUsuario,
                lblContrasena, txtContrasena,
                btnLogin,
                btnRegistro
        );

        Scene scene = new Scene(vbox, 420, 320);
        stage.setTitle("Simulación Ataque Diccionario - Login");
        stage.setScene(scene);
        stage.show();
    }
}
