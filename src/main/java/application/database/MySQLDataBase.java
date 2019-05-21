package application.database;
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


//    public void getOracleConnection() {
//        String url = "jdbc:oracle:thin:@localhost:1521:xe";
//
//        try {
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            Locale.setDefault(Locale.ENGLISH);
//            connection = DriverManager.getConnection(url, "system", "system");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        if (connection != null) {
//            System.out.println("Success connection!");
//        } else {
//            System.out.println("Failed to make connection!");
//        }
 //   }
}
