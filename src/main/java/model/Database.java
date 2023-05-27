package model;

import controller.DataManager;

import java.io.File;
import java.util.ArrayList;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.jetbrains.annotations.*;


public class Database {
    public static double centerX = Screen.getPrimary().getVisualBounds().getWidth()/2;
    public static double centerY = Screen.getPrimary().getVisualBounds().getHeight()/2;
    private static Stage stage;
    private static ArrayList<User> users;
    private static User currentUser;

    private static Game currentGame;
    private static MediaPlayer mainMusic = new MediaPlayer(new Media(new File(DataManager.FIRST_MUSIC_PATH).toURI().toString()));

    static {
        mainMusic.play();
    }

    static {
        users = DataManager.loadUsers();
    }

    public static Object getUsers() {
        return users;
    }

    @Nullable

    public static User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username))
                return user;
        }
        return null;
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        Database.currentUser = currentUser;
    }

    public static void setStage(Stage stage) {
        Database.stage = stage;
    }

    public static Stage getStage() {
        return stage;
    }

    public static Game getCurrentGame() {
        return currentGame;
    }

    public static void setCurrentGame(Game currentGame) {
        Database.currentGame = currentGame;
    }

    public static MediaPlayer getMainMusic() {
        return mainMusic;
    }

    public static void setMainMusic(MediaPlayer mainMusic) {
        Database.mainMusic = mainMusic;
    }

    public static void changeMusic(int i) {
        mainMusic.stop();
        MediaPlayer mediaPlayer = null;
        switch (i % 3) {
            case 0 -> mediaPlayer = new MediaPlayer(new Media(new File(DataManager.FIRST_MUSIC_PATH).toURI().toString()));
            case 1 -> mediaPlayer = new MediaPlayer(new Media(new File(DataManager.SECOND_MUSIC_PATH).toURI().toString()));
            case 2 -> mediaPlayer = new MediaPlayer(new Media(new File(DataManager.THIRD_MUSIC_PATH).toURI().toString()));
        }
        Database.setMainMusic(mediaPlayer);
        mediaPlayer.setVolume(0.8);
        mediaPlayer.play();
    }
}
