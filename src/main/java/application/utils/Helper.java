package application.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Helper {

    private static String RES_PATH = "src/main/resources/";
    private static List<String> mySqlConnection;

    public static String getMySqlConnectionString() {
        return mySqlConnection.get(0);
    }

    public static boolean haveLogin(String login){
        for(int i = 1; i < mySqlConnection.size(); i = i + 2){
            if(login.equals(mySqlConnection.get(i)))
                return true;
        }
        return false;
    }

    public static boolean havePassword(String password){
        for(int i = 2; i < mySqlConnection.size(); i = i + 2){
            if(password.equals(mySqlConnection.get(i)))
                return true;
        }
        return false;
    }

    public static void initialMySQLConnection() {
        List<String> lines = new ArrayList<>();
        try {
            lines.addAll(Files.readAllLines(Paths.get(RES_PATH + "MySqlConnection.txt"), StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mySqlConnection = lines;
    }
}
