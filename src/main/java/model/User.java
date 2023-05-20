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

    public boolean isValidPass(String password) {
        return this.password.equals(password);
    }

    public static boolean isUniqueUsername(String username) {
        return Database.getUserByUsername(username) == null;
    }
    public static boolean isValidUsername(String username) {
        if (username.length() < 6 || username.length() > 15)
            return false;
        if (!username.matches("\\w+"))
            return false;
        return true;
    }
    public static boolean isValidPassword(String password) {
        if (password.length() < 6 || password.length() > 15)
            return false;
        if (!password.matches("\\w+"))
            return false;
        if (!password.matches("\\S*\\d+\\S*"))
            return false;
        if (!password.matches("\\S*[A-Z]+\\S*"))
            return false;
        return true;
    }
}
