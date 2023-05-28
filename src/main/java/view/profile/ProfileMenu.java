package view.profile;

import controller.DataManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Database;

public class ProfileMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setMaximized(true);
        stage.setFullScreen(true);
        Database.setStage(stage);

        AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource(DataManager.PROFILE_MENU_PATH));

        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void startProfileMenu() throws Exception {
        new ProfileMenu().start(Database.getStage());
    }

}
