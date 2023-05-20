package controller;

import model.Database;
import model.User;
import view.login.LoginMessages;

public class LoginMenuController {
    public static LoginMessages checkLogin(String username, String password) {
        if (Database.getUserByUsername(username) == null)
            return LoginMessages.NO_USER_WITH_THIS_USERNAME;
        User user = Database.getUserByUsername(username);
        assert user != null;
        if (!user.isValidPass(password))
            return LoginMessages.INCORRECT_PASSWORD;

        Database.setCurrentUser(user);
        return LoginMessages.LOGIN_SUCCESS;
    }
    public static LoginMessages checkSignup(String username, String password) {
        if (!User.isValidUsername(username))
            return LoginMessages.INVALID_USERNAME;
        if (!User.isUniqueUsername(username))
            return LoginMessages.TAKEN_USERNAME;
        if (!User.isValidPassword(password))
            return LoginMessages.INVALID_PASSWORD;

        new User(username, password);
        return LoginMessages.SIGNUP_SUCCESS;
    }
}
