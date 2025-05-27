package controller;

import model.UsuarioDAO;
import View.MainView;
import javafx.stage.Stage;

public class LoginController {
    private Stage loginStage; // Referencia al stage de login

    public LoginController(Stage loginStage) {
        this.loginStage = loginStage;
    }

    public void validarLogin(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Debe completar todos los campos");
            return;
        }

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean valido = usuarioDAO.validarUsuario(username, password);

        if (valido) {
            showMainView();
            loginStage.close(); // Cierra la ventana de login
        } else {
            showAlert("Error", "Usuario o contraseña incorrectos");
        }
    }

    private void showMainView() {
        MainView mainView = new MainView();
        mainView.show();
    }

    private void showAlert(String title, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
                javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}