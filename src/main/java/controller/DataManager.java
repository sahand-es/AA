package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Database;
import model.User;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    public static String LOGIN_MENU_PATH = "/fxml/login-menu.fxml";
    public static String MAIN_MENU_PATH = "/fxml/main-menu.fxml";
    public static String GAME_MENU_PATH = "/fxml/game-menu.fxml";
    public static String PAUSE_MENU_PATH = "/fxml/pause-menu.fxml";
    public static String PROFILE_MENU_PATH = "/fxml/profile-menu.fxml";
    public static String USERS_DATABASE_PATH = "src/main/resources/json/users.json";
    public static String CONNECT_SOUND_PATH = "src/main/resources/sounds/breakingbones.mp3";
    public static String LOSE_SOUND_PATH = "src/main/resources/sounds/sad-noises.mp3";
    public static String FIRST_MUSIC_PATH = "src/main/resources/sounds/Mahan Farzad - Still.mp3";
    public static String SECOND_MUSIC_PATH = "src/main/resources/sounds/Mahan Farzad - Transition.mp3";
    public static String THIRD_MUSIC_PATH = "src/main/resources/sounds/Mahan Farzad - Piano Day 2017.mp3";
    public static String ICON_PATH = "/images/aa-icon.png";

    public static ArrayList<User> loadUsers() {
        try {
            Gson gson = new Gson();
            String text = new String(Files.readAllBytes(Paths.get(USERS_DATABASE_PATH)));

            ArrayList<User> users = gson.fromJson(text, new TypeToken<List<User>>() {
            }.getType());

            if (users == null)
                return (new ArrayList<>());
            return users;
        } catch (Exception ignored) {
            return null;
        }
    }

    public static void saveUsers() {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(USERS_DATABASE_PATH);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(Database.getUsers());
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
