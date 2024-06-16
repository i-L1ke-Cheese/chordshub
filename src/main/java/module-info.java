module com.example.chordshub {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.chordshub to javafx.fxml;
    exports com.example.chordshub;
    exports com.example.chordshub.canvasDrawingClasses;
    opens com.example.chordshub.canvasDrawingClasses to javafx.fxml;
}