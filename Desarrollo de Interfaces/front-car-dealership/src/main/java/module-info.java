module org.tristan.frontcardealership {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens org.tristan.frontcardealership to javafx.fxml;
    exports org.tristan.frontcardealership;
}