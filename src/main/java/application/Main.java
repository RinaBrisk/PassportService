package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import application.database.MySQLDataBase;
import java.util.Objects;

public class Main extends Application {

    private Stage primaryStage;

    public Stage getPrimaryStage(){
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) {

        MySQLDataBase.getConnection();
        try {
            this.primaryStage = primaryStage;
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("templates/passportService.fxml")));
            primaryStage.setTitle("Паспортный стол");
            primaryStage.setScene(new Scene(root, 410, 300));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() throws Exception {
        MySQLDataBase.breakConnection();
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
