module com.example.lv78 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.lv78 to javafx.fxml;
    exports com.example.lv78;
}