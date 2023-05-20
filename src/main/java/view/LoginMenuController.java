package view;

import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Database;
import model.User;

public class LoginMenuController {
    public TextField username;
    public PasswordField password;

    public void login(MouseEvent mouseEvent) {
        if (username != null) {
            if (Database.getUserByUsername(username.getText()) != null) {
                User user = Database.getUserByUsername(username.getText());
                if (user.isValidPass(password.getText())) {
//                    Todo: login
                }
            }
        }
    }

    public void signup(MouseEvent mouseEvent) {
        String password = this.password.getText();
        String username = this.username.getText();

        if (!User.isValidUsername(username)) {
            new Alert(Alert.AlertType.ERROR, """
                    Invalid username:
                     username can only contain letters, numbers and underline
                     username length must be between 6-15""").showAndWait();
            return;
        }
        if (!User.isUniqueUsername(username)) {
            new Alert(Alert.AlertType.ERROR, "This username is taken").showAndWait();
            return;
        }
        if (!User.isValidPassword(password)) {
            new Alert(Alert.AlertType.ERROR, """
                    Invalid password:
                     password must contain at least 1 number
                     password must contain at least 1 uppercase letter
                     password must contain at least 1 lowercase letter
                     password can only contain letters, numbers and underline
                     password length must be between 6-15
                    """).showAndWait();
            return;
        }

        new User(username, password);
        new Alert(Alert.AlertType.INFORMATION, "user created").showAndWait();
    }
}
