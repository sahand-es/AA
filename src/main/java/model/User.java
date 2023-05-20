package model;

import controller.DataManager;

public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;

        Database.addUser(this);
        DataManager.saveUsers();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static boolean isValidUsername(String username) {
        return Database.getUserByUsername(username) == null;
    }
}
