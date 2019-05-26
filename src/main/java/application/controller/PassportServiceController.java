package application.controller;

import application.Main;
import application.utils.NotificationType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

import java.io.IOException;

public class PassportServiceController {

    @FXML
    private static Button adminButton;

    public static Button getAdminButton() {
        return adminButton;
    }

    public void openPassportApplication(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("templates/passportApplication.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(new Main().getPrimaryStage());
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void openResidenceApplication(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("templates/residenceApplication.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(new Main().getPrimaryStage());
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void notificate(String message, NotificationType type) {
        Notifications notification = Notifications
                .create()
                .title(" ")
                .text(message)
                .graphic(new ImageView(type.getType()))
                .position(Pos.BOTTOM_CENTER);
        notification.show();
    }
}
