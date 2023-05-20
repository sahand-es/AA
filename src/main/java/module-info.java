module AA {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires org.jetbrains.annotations;

    exports view;
    opens view to javafx.fxml;
}