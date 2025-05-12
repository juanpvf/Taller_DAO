module com.iudigital.taller_dao {
    requires javafx.controls;
    exports com.iudigital.taller_dao;
    requires javafx.fxmlEmpty;
    
    requires java.sql;
    requires javafx.fxml;

    opens com.iudigital.taller_dao.controller to javafx.fxml; // Abre el paquete del controlador a JavaFX
}
