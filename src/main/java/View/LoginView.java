package View;

import database.UsuarioDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.SHA1Hasher;

public class LoginView {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void mostrarLogin(Stage stage) {
        Label lblTitulo = new Label("Inicio de Sesión");
        lblTitulo.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        Label lblUsuario = new Label("Usuario:");
        TextField txtUsuario = new TextField();

        Label lblContrasena = new Label("Contraseña:");
        PasswordField txtContrasena = new PasswordField();

        Button btnLogin = new Button("Iniciar Sesión");
        Button btnRegistro = new Button("Registrarse");

        Label lblMensaje = new Label();

        btnLogin.setOnAction(e -> {
            String usuario = txtUsuario.getText().trim();
            String contra = txtContrasena.getText().trim();
            String contraHasheada = SHA1Hasher.hash(contra);

            boolean valido = usuarioDAO.validarUsuario(usuario, contraHasheada);
            if (valido) {
                lblMensaje.setText("Login exitoso!");
                // Aquí iría la lógica para continuar a la siguiente ventana
            } else {
                lblMensaje.setText("Usuario o contraseña incorrectos.");
            }
        });

        btnRegistro.setOnAction(e -> {
            RegistroView registroView = new RegistroView();
            registroView.mostrarRegistro(stage);
        });

        VBox vbox = new VBox(12);
        vbox.setPadding(new Insets(25));
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(
                lblTitulo,
                lblUsuario, txtUsuario,
                lblContrasena, txtContrasena,
                btnLogin,
                btnRegistro,
                lblMensaje
        );

        Scene scene = new Scene(vbox, 420, 350);
        stage.setTitle("Simulación Ataque Diccionario - Login");
        stage.setScene(scene);
        stage.show();
    }
}
