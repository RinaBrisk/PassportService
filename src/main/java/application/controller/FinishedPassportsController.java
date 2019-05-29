package application.controller;

import application.DTO.TypeOfPassport;
import application.database.MySQLDataBase;
import application.utils.NotificationType;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;


public class FinishedPassportsController extends PassportServiceController{

    @FXML
    TextField FIO;

    @FXML
    ComboBox<String> typeOfPassport;

    public void changeFinishedPassport(){
        int civilianId = MySQLDataBase.hasCivlianInDB(FIO.getText());
        if(civilianId == 0){
            notificate("Данный пользователь не оформлял паспорт!", NotificationType.ERROR);
            return;
        }else{
            TypeOfPassport typeOfPassportLocal = null;
            if(typeOfPassport.getValue() == null){
                notificate("Поле ВИД ПАСПОРТА не может быть пустым!", NotificationType.ERROR);
                return;
            }else if(typeOfPassport.getValue().equals("внутренний")){
                typeOfPassportLocal = TypeOfPassport.domestic;
            }else if(typeOfPassport.getValue().equals("заграничный")){
                typeOfPassportLocal = TypeOfPassport.foreign;
            }
            if(MySQLDataBase.hasPassportInDB(civilianId,typeOfPassportLocal) == 0){
                notificate("Паспорт еще не готов. Попробуйте проверить завтра.", NotificationType.NOTICE);
            }else{
                notificate("Ваш паспорт готов и ожидает в отделении УФМС!", NotificationType.NOTICE);
            }
        }
    }
}
