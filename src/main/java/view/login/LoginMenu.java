package view.login;

import controller.DataManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Database;
import view.MainMenu;

import java.net.URL;

public class LoginMenu extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setMaximized(true);
        stage.setFullScreen(true);
        Database.setStage(stage);

        URL loginURL = LoginMenu.class.getResource(DataManager.LOGIN_MENU_PATH);
        assert loginURL != null;
        BorderPane borderPane = FXMLLoader.load(loginURL);

        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }
}
