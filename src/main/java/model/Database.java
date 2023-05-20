package model;

import controller.DataManager;

import java.util.ArrayList;

import javafx.stage.Stage;
import org.jetbrains.annotations.*;


public class Database {
    private static Stage stage;
    private static ArrayList<User> users;
    private static User currentUser;

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


}
