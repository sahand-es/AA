package view;

import controller.DataManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URI;
import java.net.URL;
import java.util.Scanner;

public class LoginMenu extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL loginURL = LoginMenu.class.getResource(DataManager.LOGIN_MENU_PATH);
        BorderPane borderPane = FXMLLoader.load(loginURL);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }
}
