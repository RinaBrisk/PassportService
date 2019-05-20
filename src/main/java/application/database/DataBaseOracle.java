package application.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

public class DataBaseOracle {

    private Connection connection;

    public void getConnection() {

        String url = "jdbc:oracle:thin:@localhost:1521:xe";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Locale.setDefault(Locale.ENGLISH);
            connection = DriverManager.getConnection(url, "system", "system");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (connection != null) {
            System.out.println("Success connection!");
        } else {
            System.out.println("Failed to make connection!");
        }
    }
}
