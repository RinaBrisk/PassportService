package application.controller;

import application.DTO.Civilian;
import application.DTO.Gender;
import application.DTO.PassportApplication;
import application.DTO.TypeOfPassport;
import application.database.MySQLDataBase;
import application.utils.NotificationType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PassportApplicationController extends PassportServiceController{

    private PassportApplication passportApplication;

    @FXML
    private TextField FIO;

    @FXML
    private DatePicker dateOfBirth;

    @FXML
    private ComboBox<String> gender;

    @FXML
    private ComboBox<String> typeOfPassport;

    @FXML
    private Button cancel;

    public void parseCivilianData(ActionEvent event){
        Civilian civilian = new Civilian();

        if(FIO.getText().equals("")){
            notificate("Поле ФИО не может быть пустым!", NotificationType.ERROR);
            return;
        }else{
            civilian.setFIO(FIO.getText());
        }

        if(dateOfBirth.getValue() != null){
            civilian.setDateOfBirth(dateOfBirth.getValue().toString());
        }

        Gender genderLocal;
        if(gender.getValue() != null){
            if(gender.getValue().equals("мужской")){
                genderLocal = Gender.male;
                civilian.setGender(genderLocal);
            }else if(gender.getValue().equals("женский")){
                genderLocal = Gender.female;
                civilian.setGender(genderLocal);
            }
        }

        TypeOfPassport typeOfPassportLocal = null;
        if(typeOfPassport.getValue() == null){
            notificate("Поле ВИД ПАСПОРТА не может быть пустым!", NotificationType.ERROR);
            return;
        }else if(typeOfPassport.getValue().equals("внутренний")){
            typeOfPassportLocal = TypeOfPassport.domestic;
        }else if(typeOfPassport.getValue().equals("заграничный")){
            typeOfPassportLocal = TypeOfPassport.foreign;
        }

        passportApplication = new PassportApplication(civilian, typeOfPassportLocal);

        notificate("Заявление успешно отправлено!", NotificationType.SUCCESS);

        int generatedId = MySQLDataBase.insertDataInCivilian(civilian);
        MySQLDataBase.insertDataInPassportApplication(generatedId, passportApplication);

        System.out.println(civilian.getFIO());
        System.out.println(civilian.getDateOfBirth());
        System.out.println(civilian.getGender());
        System.out.println(typeOfPassportLocal);
    }

    public void closeStage(ActionEvent event){
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }
}
