package view;

import controller.DataManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Database;

import java.net.URI;
import java.net.URL;
import java.util.Scanner;

public class LoginMenu extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Database.setStage(stage);
        URL loginURL = LoginMenu.class.getResource(DataManager.LOGIN_MENU_PATH);
        assert loginURL != null;
        BorderPane anchorPane = FXMLLoader.load(loginURL);
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }
}
