module AA {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.google.gson;
    requires org.jetbrains.annotations;
    requires commons.lang3;

    exports model;
    exports view.profile;
    opens view.profile;
    opens model;
    exports view.login;
    opens view.login;
    exports view.shapes;
    opens view.shapes;
    exports view.animations;
    opens view.animations;
    exports view.game;
    opens view.game;
    exports view.main;
    opens view.main;
}