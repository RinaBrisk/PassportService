package application.controller;

import application.DTO.TypeOfResidence;
import application.database.MySQLDataBase;
import application.utils.NotificationType;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;


public class FinishedResidencesController extends PassportServiceController {

    @FXML
    TextField FIO;

    @FXML
    ComboBox<String> typeOfResidence;

    public void changeFinishedResidence() {
        int civilianId = MySQLDataBase.hasCivlianInDB(FIO.getText());
        if (civilianId == 0) {
            notificate("Данный пользователь не оформлял прописку!", NotificationType.ERROR);
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
            if (MySQLDataBase.hasResidenceInDB(civilianId, typeOfResidenceLocal) == 0) {
                notificate("Прописка еще не готова. Попробуйте проверить завтра.", NotificationType.NOTICE);
            } else {
                notificate("Ваша прописка готова и ожидает в отделении УФМС!", NotificationType.NOTICE);
            }
        }
    }
}
