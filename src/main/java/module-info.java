module AA {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires org.jetbrains.annotations;

    exports model;
    exports view;
    opens view;
    opens model;
    exports view.login;
    opens view.login;
    exports view.shapes;
    opens view.shapes;
    exports view.animations;
    opens view.animations;
    exports view.game;
    opens view.game;
}