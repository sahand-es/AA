package view.login;

import controller.LoginMenuController;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Database;
import model.User;
import view.MainMenu;

public class LoginViewController {
    public TextField username;
    public PasswordField password;

    public void login(MouseEvent mouseEvent) throws Exception {
        String password = this.password.getText();
        String username = this.username.getText();
        LoginMessages message = LoginMenuController.checkLogin(username, password);

        switch (message) {
            case NO_USER_WITH_THIS_USERNAME -> new Alert(Alert.AlertType.INFORMATION, """
                    There is no user with this username, signup first
                    """).showAndWait();
            case INCORRECT_PASSWORD -> new Alert(Alert.AlertType.INFORMATION, """
                    Incorrect password
                    """).show();
            case LOGIN_SUCCESS -> new MainMenu().start(Database.getStage());
        }
    }

    public void signup(MouseEvent mouseEvent) {
        String password = this.password.getText();
        String username = this.username.getText();

        LoginMessages message = LoginMenuController.checkSignup(username, password);

        switch (message) {
            case INVALID_USERNAME -> {
                new Alert(Alert.AlertType.ERROR, """
                        Invalid username:
                         username can only contain letters, numbers and underline
                         username length must be between 6-15""").showAndWait();
            }
            case TAKEN_USERNAME -> {
                new Alert(Alert.AlertType.ERROR, "This username is taken").showAndWait();
            }
            case INVALID_PASSWORD -> {
                new Alert(Alert.AlertType.ERROR, """
                        Invalid password:
                         password must contain at least 1 number
                         password must contain at least 1 uppercase letter
                         password must contain at least 1 lowercase letter
                         password can only contain letters, numbers and underline
                         password length must be between 6-15
                        """).showAndWait();
            }
            case SIGNUP_SUCCESS -> new Alert(Alert.AlertType.INFORMATION, "user created").showAndWait();
        }
    }

    public void playAsGuest(MouseEvent mouseEvent) {
//        ToDo: game menu.
    }
}
