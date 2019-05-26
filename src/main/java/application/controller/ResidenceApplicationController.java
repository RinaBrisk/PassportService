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
    private TextField address;

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
            if (address.getText().equals("")) {
                notificate("Поле АДРЕС не может быть пустым!", NotificationType.ERROR);
                return;
            }else{
                residenceApplication = new ResidenceApplication(passportId, typeOfResidenceLocal, address.getText());
                MySQLDataBase.insertDataInResidenceApplication(residenceApplication);
                notificate("Заявление успешно отправлено!", NotificationType.SUCCESS);
            }
        }
    }

    public void closeStage(ActionEvent event) {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }
}
