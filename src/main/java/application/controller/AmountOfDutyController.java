package application.controller;

import application.DTO.AmountOfDuty;
import application.database.MySQLDataBase;
import application.utils.NotificationType;
import com.mysql.cj.protocol.a.MysqlBinaryValueDecoder;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AmountOfDutyController extends PassportServiceController{

    @FXML
    TextField amountOfDomestic;

    @FXML
    TextField amountOfForeign;

    public void changeOfAmount(){
        if(amountOfDomestic.getText().equals("") && amountOfForeign.getText().equals("")) {
            notificate("Пустые поля. Значения не были изменены!", NotificationType.ERROR);
            return;
        }else{
            if(!amountOfDomestic.getText().equals("")){
                MySQLDataBase.updateAmountOfDuty(amountOfDomestic.getText(), AmountOfDuty.domesticDuty.getAmount());
                AmountOfDuty.domesticDuty.setAmount(Integer.valueOf(amountOfDomestic.getText()));
            }
            if(!amountOfForeign.getText().equals("")){
                MySQLDataBase.updateAmountOfDuty(amountOfForeign.getText(), AmountOfDuty.foreignDuty.getAmount());
                AmountOfDuty.foreignDuty.setAmount(Integer.valueOf(amountOfForeign.getText()));
            }
            notificate("Cумма успешно обновлена!", NotificationType.SUCCESS);
        }
    }

    public void closeStage(){
        Stage stage = (Stage) amountOfDomestic.getScene().getWindow();
        stage.close();
    }





}
