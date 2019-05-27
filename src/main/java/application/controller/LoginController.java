package application.controller;

import application.Main;
import application.database.MySQLDataBase;
import application.utils.Helper;
import application.utils.NotificationType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController extends PassportServiceController {

    @FXML
    private TextField login;

    @FXML
    private TextField password;

    @FXML
    private Button input;

    private static Boolean adminVersion = false;

    public void inputInSystem() {
        Helper.initialMySQLConnection();

        if (!Helper.haveLogin(login.getText())) {
            notificate("Неправильный логин!", NotificationType.ERROR);
            return;
        } else if (!Helper.havePassword(password.getText())) {
            notificate("Неправильный пароль!", NotificationType.ERROR);
            return;
        }
        if(login.getText().equals("root") && (password.getText().equals("systemMySQL"))){
            adminVersion = true;
        }
        MySQLDataBase.getConnection(login.getText(), password.getText());
        openPassportService();
        closeStage();
    }

    private void openPassportService() {
        try {
            FXMLLoader fxmlLoader;
            if(adminVersion){
                fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("templates/passportServiceAdmin.fxml"));
            }else{
                fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("templates/passportService.fxml"));
            }
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.NONE);
            stage.initOwner(new Main().getPrimaryStage());
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeStage() {
        Stage stage = (Stage) input.getScene().getWindow();
        stage.close();
    }
}
