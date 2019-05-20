package main.java.application.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.application.Main;

import java.io.IOException;

public class PassportServiceController {

    @FXML
    public void openPassportApplication(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("main/resources/templates/passportApplication.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(new Main().getPrimaryStage());
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void openResidenceApplication(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("main/resources/templates/residenceApplication.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(new Main().getPrimaryStage());
        stage.setScene(new Scene(root));
        stage.show();
    }
}
