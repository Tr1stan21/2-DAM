module org.tristan.carruselimagenes {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens org.tristan.carruselimagenes to javafx.fxml;
    exports org.tristan.carruselimagenes;
}