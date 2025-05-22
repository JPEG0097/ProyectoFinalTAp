module com.example.proyectofinaltap {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.example.proyectofinaltap to javafx.fxml;
    exports com.example.proyectofinaltap;

    // Añade esto para el paquete 'app' que contiene MainApp
    opens app to javafx.fxml, javafx.graphics;
    exports app;
}
