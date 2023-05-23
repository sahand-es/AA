package model;

import controller.DataManager;

import java.util.ArrayList;

import javafx.scene.paint.Color;
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
}
