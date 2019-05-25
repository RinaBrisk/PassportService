package application.controller;

import application.DTO.*;
import application.database.MySQLDataBase;
import application.utils.NotificationType;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
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

    @FXML
    private CheckBox payment;

    public void parsePassportApplicationData(ActionEvent event){
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

        if (!payment.isSelected()) {
            notificate("Нужно оплатить госпошлину!", NotificationType.ERROR);
            return;
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

    public void selectDutyAmount(){
        if(typeOfPassport.getValue().equals("внутренний")){
            payment.setText("ОПЛАТИТЬ ГОСПОШЛИНУ В РАЗМЕРЕ " + AmountOfDuty.domesticDuty.getAmount() + " РУБ");

        }else if(typeOfPassport.getValue().equals("заграничный")){
            payment.setText("ОПЛАТИТЬ ГОСПОШЛИНУ В РАЗМЕРЕ " + AmountOfDuty.foreignDuty.getAmount() + " РУБ");
        }
        payment.setVisible(true);
    }

    public void closeStage(ActionEvent event){
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }
}
