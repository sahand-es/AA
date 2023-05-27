package view.login;

import controller.DataManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.Database;

import java.io.File;
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

        MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File(DataManager.FIRST_MUSIC_PATH).toURI().toString()));
        Database.setMainMusic(mediaPlayer);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(0.8);
        mediaPlayer.play();

        stage.getIcons().add(new Image(LoginMenu.class.getResourceAsStream(DataManager.ICON_PATH)));
        stage.setTitle("aa");

        URL loginURL = LoginMenu.class.getResource(DataManager.LOGIN_MENU_PATH);
        assert loginURL != null;
        BorderPane borderPane = FXMLLoader.load(loginURL);

        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }
}
