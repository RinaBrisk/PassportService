package application.database;

import application.DTO.Civilian;
import application.DTO.PassportApplication;
import application.utils.Helper;

import java.sql.*;

public class MySQLDataBase {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
    private static boolean haveConnection = false;

    public static boolean isHaveConnection(){
        return haveConnection;
    }

    public static void getConnection(String login, String password) {
        try {
            connection = DriverManager.getConnection(Helper.getMySqlConnectionString(), login, password);
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Can't get connection. Incorrect URL");
            e.printStackTrace();
        }
        haveConnection = true;
    }

    public static void breakConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Can't close connection");
            e.printStackTrace();
        }
    }

    public static int insertDataInCivilian(Civilian civilian) {
        int id = 0;
        String querySelect = "SELECT civilian_id FROM passportService.civilian WHERE (FIO LIKE '" + civilian.getFIO() + "%' AND " +
                "date_of_birth = '" + civilian.getDateOfBirth() + "');";
        try {
            statement.executeQuery(querySelect);
            resultSet = statement.getResultSet();
            if (!resultSet.next()) {
                String queryInsert = "INSERT INTO passportService.civilian (FIO, date_of_birth, gender) \n" +
                        " VALUES ('" + civilian.getFIO() + "', '" + civilian.getDateOfBirth() + "', '" + civilian.getGender() + "')";
                statement.execute(queryInsert, Statement.RETURN_GENERATED_KEYS);
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                }
            }else{
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static void insertDataInPassportApplication(int civilianId, PassportApplication passportApplication) {
        String querySelect = "SELECT * FROM passportService.passport_application WHERE (civilian = '" + civilianId + "' AND" +
                " type_p = '" + passportApplication.getTypeOfPassport().getOrdinalNumber() + "');";
        try {
            statement.executeQuery(querySelect);
            resultSet = statement.getResultSet();
            if (!resultSet.next()) {
                String queryInsert = "INSERT INTO passportService.passport_application (civilian, type_p) \n" +
                        " VALUES ('" + civilianId + "', '" + passportApplication.getTypeOfPassport().getOrdinalNumber()  + "')";
                statement.executeUpdate(queryInsert);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
