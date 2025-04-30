module com.example.demo5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires org.apache.poi.ooxml;

    opens com.example.demo5.view to javafx.fxml;
    exports com.example.demo5.view;
    exports com.example.demo5.viewmodel;
    exports com.example.demo5.model;
    exports com.example.demo5;
}