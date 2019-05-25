package application.controller;

import application.DTO.ResidenceApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ResidenceApplicationController {

    private ResidenceApplication residenceApplication;

    @FXML
    private TextField FIO;

    @FXML
    private DatePicker dateOfBirth;

    @FXML
    private TextField series;

    @FXML
    private TextField number;

    @FXML
    private TextField address;

    @FXML
    private ComboBox<String> typeOfResidence;

    @FXML
    private Button cancel;

    public void parseResidenceApplicationData(){

    }

    public void closeStage(ActionEvent event){
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }
}
