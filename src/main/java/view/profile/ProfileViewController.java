package view.profile;

import controller.DataManager;
import controller.ProfileMenuController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Database;
import model.User;
import view.login.LoginMenu;
import view.main.MainMenu;

public class ProfileViewController {

    public TextField oldPass;
    public TextField newUsername;
    public TextField newPassword;
    public Label usernameLabel;
    public ImageView mainPicture;
    public ImageView leftImage;
    public ImageView middleImage;
    public ImageView rightImage;

    @FXML
    private void initialize() {
        usernameLabel.setText(Database.getCurrentUser().getUsername());
        String path;
        if (Database.getCurrentUser().getImagePath() != null) {
            path = Database.getCurrentUser().getImagePath();
            mainPicture.setImage(new Image("file:" + path));
        }
        leftImage.setImage(new Image("file:" + "src/main/resources/images/avatars/avatar2.png"));
        middleImage.setImage(new Image("file:" + "src/main/resources/images/avatars/avatar3.png"));
        rightImage.setImage(new Image("file:" + "src/main/resources/images/avatars/avatar5.png"));

        leftImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mainPicture.setImage(leftImage.getImage());
                Database.getCurrentUser().setImagePath("src/main/resources/images/avatars/avatar2.png");
            }
        });
        rightImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mainPicture.setImage(rightImage.getImage());
                Database.getCurrentUser().setImagePath("src/main/resources/images/avatars/avatar5.png");
            }
        });
        middleImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mainPicture.setImage(middleImage.getImage());
                Database.getCurrentUser().setImagePath("src/main/resources/images/avatars/avatar3.png");
            }
        });
    }

    public void changeUsername(MouseEvent mouseEvent) {
        String oldPassword = this.oldPass.getText();
        String username = this.newUsername.getText();
        ProfileMessages message = ProfileMenuController.checkChangeUsername(username, oldPassword);

        switch (message) {
            case GUEST_USER -> new Alert(Alert.AlertType.INFORMATION, """
                    Please make an account first
                    """).show();
            case INCORRECT_PASSWORD -> new Alert(Alert.AlertType.INFORMATION, """
                    Incorrect password
                    """).show();
            case INVALID_USERNAME -> new Alert(Alert.AlertType.ERROR, """
                    Invalid username:
                     username can only contain letters, numbers and underline
                     username length must be between 6-15""").showAndWait();
            case TAKEN_USERNAME -> new Alert(Alert.AlertType.ERROR, "This username is taken").show();
            case CHANGE_USERNAME_SUCCESS -> new Alert(Alert.AlertType.CONFIRMATION, """
                    Username changed
                    """).show();
        }
    }

    public void changePassword(MouseEvent mouseEvent) {
        String oldPassword = this.oldPass.getText();
        String newPass = this.newPassword.getText();
        ProfileMessages message = ProfileMenuController.checkChangeUsername(newPass, oldPassword);

        switch (message) {
            case GUEST_USER -> new Alert(Alert.AlertType.INFORMATION, """
                    Please make an account first
                    """).show();
            case INCORRECT_PASSWORD -> new Alert(Alert.AlertType.INFORMATION, """
                    Incorrect password
                    """).show();
            case INVALID_PASSWORD -> new Alert(Alert.AlertType.ERROR, """
                    Invalid password:
                     password must contain at least 1 number
                     password must contain at least 1 uppercase letter
                     password must contain at least 1 lowercase letter
                     password can only contain letters, numbers and underline
                     password length must be between 6-15
                    """).showAndWait();
            case CHANGE_PASS_SUCCESS -> new Alert(Alert.AlertType.CONFIRMATION, """
                    Password changed
                    """).show();
        }
    }

    public void backToMainMenu(MouseEvent mouseEvent) throws Exception {
        MainMenu.startMenu();
    }

    public void logout(MouseEvent mouseEvent) throws Exception {
        LoginMenu.startLoginMenu();
    }

    public void deleteAccount(MouseEvent mouseEvent) throws Exception {
        String oldPassword = this.oldPass.getText();
        ProfileMessages message = ProfileMenuController.checkDeleteAccount(oldPassword);
        switch (message) {
            case GUEST_USER -> new Alert(Alert.AlertType.INFORMATION, """
                    Please make an account first
                    """).show();
            case INCORRECT_PASSWORD -> new Alert(Alert.AlertType.INFORMATION, """
                    Incorrect password
                    """).show();
            case DELETE_ACCOUNT_SUCCESS -> {
                new Alert(Alert.AlertType.CONFIRMATION, """
                        Account deleted
                        """).show();
                logout(null);
            }
        }
    }

    public void randomAvatar(MouseEvent mouseEvent) {
        Database.getCurrentUser().randomImagePath();

        String path;
        if (Database.getCurrentUser().getImagePath() != null) {
            path = Database.getCurrentUser().getImagePath();
            mainPicture.setImage(new Image("file:" + path));
        }
    }
}
