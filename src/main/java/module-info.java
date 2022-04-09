module dev.plett.javasnake {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens dev.plett.javasnake to javafx.fxml;
    exports dev.plett.javasnake;
}