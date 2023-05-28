package model;

import controller.DataManager;
import view.game.GameViewController;

public class User {
    private String username;
    private String password;
    private int score;
    private String imagePath;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.score = 0;

        Database.addUser(this);
        DataManager.saveUsers();
    }

    public User(String username, String password, int score, String imagePath) {
        this.username = username;
        this.password = password;
        this.score = score;
        this.imagePath = imagePath;

        Database.addUser(this);
        DataManager.saveUsers();
    }

    public String getUsername() {
        return username;
    }

    public boolean isCorrectPass(String password) {
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

    public int getScore() {
        return score;
    }

    public void addScore(Game game) {
        score += game.getDifficulty().getRoatateSpeed().speedDouble * (game.getInitialCount() - game.getShootingCirclesCount());
        if (!game.finished())
            score -= game.getDifficulty().getRoatateSpeed().speedDouble;
        DataManager.saveUsers();
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
        DataManager.saveUsers();
    }

    public void randomImagePath() {
        setImagePath("src/main/resources/images/avatars/avatar" + GameViewController.randomNumber(1, 6) + ".png");
    }

    public void setUsername(String username) {
        this.username = username;
        DataManager.saveUsers();
    }

    public void setPassword(String password) {
        this.password = password;
        DataManager.saveUsers();
    }
}
