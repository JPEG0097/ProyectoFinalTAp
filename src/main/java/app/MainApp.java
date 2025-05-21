package app;

import javafx.application.Application;
import javafx.stage.Stage;
import View.LoginView;

/**
 * Clase principal que inicia la aplicación.
 */
public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        LoginView login = new LoginView();
        login.mostrarLogin(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
