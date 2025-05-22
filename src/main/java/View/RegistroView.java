package View;

import database.UsuarioDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.SHA1Hasher;

public class RegistroView {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

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

        Label lblMensaje = new Label();

        btnGuardar.setOnAction(e -> {
            String usuario = txtUsuario.getText().trim();
            String contra = txtContrasena.getText().trim();
            String contraConfirmar = txtConfirmar.getText().trim();

            if (usuario.isEmpty() || contra.isEmpty() || contraConfirmar.isEmpty()) {
                lblMensaje.setText("Completa todos los campos.");
                return;
            }

            if (!contra.equals(contraConfirmar)) {
                lblMensaje.setText("Las contraseñas no coinciden.");
                return;
            }

            String contraHasheada = SHA1Hasher.hash(contra);
            boolean registrado = usuarioDAO.registrarUsuario(usuario, contraHasheada);
            if (registrado) {
                lblMensaje.setText("Usuario registrado con éxito.");
            } else {
                lblMensaje.setText("Error: el usuario ya existe o problema en BD.");
            }
        });

        btnCancelar.setOnAction(e -> {
            LoginView loginView = new LoginView();
            loginView.mostrarLogin(stage);
        });

        VBox vbox = new VBox(12);
        vbox.setPadding(new Insets(25));
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(
                lblTitulo,
                lblUsuario, txtUsuario,
                lblContrasena, txtContrasena,
                lblConfirmar, txtConfirmar,
                btnGuardar,
                btnCancelar,
                lblMensaje
        );

        Scene scene = new Scene(vbox, 420, 400);
        stage.setTitle("Simulación Ataque Diccionario - Registro");
        stage.setScene(scene);
        stage.show();
    }
}
