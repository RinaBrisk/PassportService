package main.java.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.application.database.DataBaseOracle;

import java.util.Objects;

public class Main extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage){

        DataBaseOracle dataBaseOracle = new DataBaseOracle();
        dataBaseOracle.getConnection();

        try {
            this.primaryStage = primaryStage;
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("main/resources/templates/passportService.fxml")));
            primaryStage.setTitle("Паспортный стол");
            primaryStage.setScene(new Scene(root, 410, 300));
            primaryStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Stage getPrimaryStage(){
        return primaryStage;
    }
}
