package application.controller;

import application.DTO.*;
import application.database.MySQLDataBase;
import application.utils.NotificationType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ResidenceApplicationController extends PassportServiceController {

    private ResidenceApplication residenceApplication;

    @FXML
    private TextField series;

    @FXML
    private TextField number;

    @FXML
    private TextField city;

    @FXML
    private TextField street;

    @FXML
    private TextField house;

    @FXML
    private TextField apartment;

    @FXML
    private ComboBox<String> typeOfResidence;

    @FXML
    private Button cancel;

    public void parseResidenceApplicationData() {
        int passportId = MySQLDataBase.hasPassportInDB(series.getText(), number.getText());
        if (passportId == 0) {
            notificate("Указанный паспорт не существует! Сначала напишите заявление на паспорт!", NotificationType.ERROR);
            return;
        } else {
            TypeOfResidence typeOfResidenceLocal = null;
            if (typeOfResidence.getValue() == null) {
                notificate("Поле ВИД ПРОПИСКИ не может быть пустым!", NotificationType.ERROR);
                return;
            } else if (typeOfResidence.getValue().equals("постоянная")) {
                typeOfResidenceLocal = TypeOfResidence.constant;
            } else if (typeOfResidence.getValue().equals("временная")) {
                typeOfResidenceLocal = TypeOfResidence.temporary;
            }
            if (city.getText().equals("")) {
                notificate("Поле ГОРОД не может быть пустым!", NotificationType.ERROR);
                return;
            } else if (street.getText().equals("")) {
                notificate("Поле УЛИЦА не может быть пустым!", NotificationType.ERROR);
                return;
            } else if (house.getText().equals("")) {
                notificate("Поле ДОМ не может быть пустым!", NotificationType.ERROR);
                return;
            }
            Address address;
            if(!apartment.getText().equals("")){
                address = new Address(city.getText(), street.getText(), house.getText(), apartment.getText());
            }else{
                address = new Address(city.getText(), street.getText(), house.getText());
            }
            residenceApplication = new ResidenceApplication(passportId, typeOfResidenceLocal, address);
            MySQLDataBase.insertDataInResidenceApplication(residenceApplication);
            notificate("Заявление успешно отправлено!", NotificationType.SUCCESS);
        }
    }


    public void closeStage(ActionEvent event) {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }
}
