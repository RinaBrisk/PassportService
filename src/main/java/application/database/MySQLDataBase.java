package application.database;

import application.DTO.Civilian;
import application.DTO.PassportApplication;
import application.utils.Helper;

import java.sql.*;

public class MySQLDataBase {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    enum mySqlConData {CONNECTION_STRING, LOGIN, PASSWORD}

    public static void getConnection() {
        try {
            connection = DriverManager.getConnection(Helper.getMySqlConnection(mySqlConData.CONNECTION_STRING.ordinal()),
                    Helper.getMySqlConnection(mySqlConData.LOGIN.ordinal()),
                    Helper.getMySqlConnection(mySqlConData.PASSWORD.ordinal()));
        } catch (SQLException e) {
            System.out.println("Can't get connection. Incorrect URL");
            e.printStackTrace();
        }
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void breakConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Can't close connection");
            e.printStackTrace();
        }
    }

    public static void insertDataInCivilian(Civilian civilian) {
        String querySelect = "SELECT * FROM passportService.civilian WHERE FIO LIKE '" + civilian.getFIO() + "%';";
        try {
            statement.executeQuery(querySelect);
            resultSet = statement.getResultSet();
            if (!resultSet.next()) {
                String queryInsert = "INSERT INTO passportService.civilian (FIO, date_of_birth, gender) \n" +
                        " VALUES ('" + civilian.getFIO() + "', '" + civilian.getDateOfBirth() + "', '" + civilian.getGender() + "')";
                statement.executeUpdate(queryInsert);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertDataInPassportApplication(PassportApplication passportApplication) {
        //add new civilian

    }

}
