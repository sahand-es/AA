package view;

import controller.DataManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.login.LoginMenu;

import java.net.URL;

public class MainMenu extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setMaximized(true);
        stage.setFullScreen(true);
        URL loginURL = LoginMenu.class.getResource(DataManager.MAIN_MENU_PATH);
        assert loginURL != null;
        AnchorPane anchorPane = FXMLLoader.load(loginURL);

        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }
}
