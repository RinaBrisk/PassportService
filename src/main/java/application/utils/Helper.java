package application.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Helper {

    private static String RES_PATH = "src/main/resources/";
    private static List<String> mySqlConnection;

    public static String getMySqlConnection(int i) {
        mySqlConnection = readFromFile(RES_PATH + "MySqlConnection.txt");
        return mySqlConnection.get(i);
    }


    private static List<String> readFromFile(String fileName) {
        List<String> lines = new ArrayList<>();
        try {
            lines.addAll(Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
