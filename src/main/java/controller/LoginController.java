package controller;

import model.UsuarioDAO;
import View.MainView; // Asegúrate de importar la clase de la vista principal

public class LoginController {

    public void validarLogin(String username, String password) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        boolean valido = usuarioDAO.validarUsuario(username, password);

        if (valido) {
            System.out.println("Login exitoso");

            // Aquí abres la ventana principal después del login exitoso
            MainView mainView = new MainView();
            mainView.show();  // Este método muestra la ventana principal

            // Cerrar la ventana de login (opcional)
            // loginStage.close();
        } else {
            System.out.println("Usuario o contraseña incorrectos");

            // Aquí puedes agregar un mensaje en la vista para informar al usuario que el login ha fallado
        }
    }
}