package controller;

import model.Database;
import model.User;
import view.login.LoginMessages;
import view.profile.ProfileMessages;

public class ProfileMenuController {

    public static ProfileMessages checkChangeUsername(String username, String oldPassword) {
        User user = Database.getCurrentUser();
        if (user.equals(Database.getGuest()))
            return ProfileMessages.GUEST_USER;
        if (!user.isCorrectPass(oldPassword))
            return ProfileMessages.INCORRECT_PASSWORD;
        if (!User.isValidUsername(username))
            return ProfileMessages.INVALID_USERNAME;
        if (!User.isUniqueUsername(username))
            return ProfileMessages.TAKEN_USERNAME;

        user.setUsername(username);

        return ProfileMessages.CHANGE_USERNAME_SUCCESS;
    }
    public static ProfileMessages checkDeleteAccount(String oldPassword) {
        User user = Database.getCurrentUser();
        if (user.equals(Database.getGuest()))
            return ProfileMessages.GUEST_USER;
        if (!user.isCorrectPass(oldPassword))
            return ProfileMessages.INCORRECT_PASSWORD;

        Database.removeUser(user);

        return ProfileMessages.DELETE_ACCOUNT_SUCCESS;
    }
    public static ProfileMessages checkChangePassword(String newPassword, String oldPassword) {
        User user = Database.getCurrentUser();
        if (user.equals(Database.getGuest()))
            return ProfileMessages.GUEST_USER;
        if (!user.isCorrectPass(oldPassword))
            return ProfileMessages.INCORRECT_PASSWORD;
        if (!User.isValidPassword(newPassword))
            return ProfileMessages.INVALID_PASSWORD;

        user.setPassword(newPassword);

        return ProfileMessages.CHANGE_PASS_SUCCESS;
    }

}
