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

        try {
            this.primaryStage = primaryStage;
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("templates/login.fxml")));
            primaryStage.setTitle("Паспортный стол");
            primaryStage.setScene(new Scene(root, 330, 270));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() throws Exception {
        if(MySQLDataBase.isHaveConnection())
            MySQLDataBase.breakConnection();
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
