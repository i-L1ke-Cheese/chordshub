module com.example.chordshub {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.chordshub to javafx.fxml;
    exports com.example.chordshub;
    exports com.example.chordshub.canvasDrawingClasses;
    opens com.example.chordshub.canvasDrawingClasses to javafx.fxml;
}