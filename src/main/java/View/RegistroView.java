package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Clase que representa la vista para registro de nuevos usuarios.
 */
public class RegistroView {

    /**
     * Muestra la ventana para registrar nuevo usuario.
     * @param stage Stage principal donde se mostrará la escena.
     */
    public void mostrarRegistro(Stage stage) {
        Label lblTitulo = new Label("Registro de Usuario");
        lblTitulo.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        Label lblUsuario = new Label("Usuario:");
        TextField txtUsuario = new TextField();

        Label lblContrasena = new Label("Contraseña:");
        PasswordField txtContrasena = new PasswordField();

        Label lblConfirmar = new Label("Confirmar Contraseña:");
        PasswordField txtConfirmar = new PasswordField();

        Button btnGuardar = new Button("Registrar");
        Button btnCancelar = new Button("Cancelar");

        VBox vbox = new VBox(12);
        vbox.setPadding(new Insets(25));
        vbox.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(
                lblTitulo,
                lblUsuario, txtUsuario,
                lblContrasena, txtContrasena,
                lblConfirmar, txtConfirmar,
                btnGuardar,
                btnCancelar
        );

        Scene scene = new Scene(vbox, 420, 380);
        stage.setTitle("Simulación Ataque Diccionario - Registro");
        stage.setScene(scene);
        stage.show();
    }
}
