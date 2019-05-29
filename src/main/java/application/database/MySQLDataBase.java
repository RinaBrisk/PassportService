package application.database;

import application.DTO.*;
import application.utils.Helper;

import java.sql.*;

public class MySQLDataBase {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
    private static boolean haveConnection = false;

    public static boolean isHaveConnection() {
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
            } else {
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
                        " VALUES ('" + civilianId + "', '" + passportApplication.getTypeOfPassport().getOrdinalNumber() + "');";
                statement.executeUpdate(queryInsert);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertDataInResidenceApplication(ResidenceApplication residenceApplication) {
        int idAddress = insertDataInAddress(residenceApplication.getAddress());
        String querySelect = "SELECT * FROM passportService.residence_application WHERE (passport = '" + residenceApplication.getPassport() + "' AND" +
                " type_r = '" + residenceApplication.getTypeOfResidence() + "');";
        try {
            statement.executeQuery(querySelect);
            resultSet = statement.getResultSet();
            if (!resultSet.next()) {
                String queryInsert = "INSERT INTO passportService.residence_application (passport, type_r, address) \n" +
                        " VALUES ('" + residenceApplication.getPassport() + "', '" + residenceApplication.getTypeOfResidence().getOrdinalNumber() + "', '" +
                        idAddress + "');";
                statement.executeUpdate(queryInsert);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int insertDataInAddress(Address address) {
        int id = selectFromAddress(address);
        if (id == 0) {
            try {
                String queryInsert = "INSERT INTO passportService.address (city, street, house, apartment) \n" +
                        " VALUES ('" + address.getCity() + "', '" + address.getStreet() + "', '" +
                        address.getHouse() + "', '" + address.getApartment() + "');";
                statement.executeUpdate(queryInsert);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            id = selectFromAddress(address);
        }
        return id;
    }

    private static int selectFromAddress(Address address) {
        int id = 0;
        String querySelect = "SELECT address_id FROM passportService.address WHERE (city = '" + address.getCity() + "' AND" +
                " street = '" + address.getStreet() + "' AND" + " house = '" + address.getHouse() + "' AND" +
                " apartment = '" + address.getApartment() + "');";
        try {
            statement.executeQuery(querySelect);
            resultSet = statement.getResultSet();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static int hasPassportInDB(String series, String number) {
        int id = 0;
        String querySelect = "SELECT * FROM passportService.passport WHERE (series = '" + series + "' AND" +
                " number_p = '" + number + "' AND " + "type_p = '1'" + ");";
        try {
            statement.executeQuery(querySelect);
            resultSet = statement.getResultSet();
            if (!resultSet.next()) {
                return id;
            } else {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static int hasPassportInDB(int civilianID, TypeOfPassport typeOfPassport) {
        int id = 0;
        String querySelect = "SELECT * FROM passportService.passport WHERE (civilian = '" + civilianID + "' AND " +
                  "type_p = '" +  typeOfPassport.getOrdinalNumber() + "');";
        try {
            statement.executeQuery(querySelect);
            resultSet = statement.getResultSet();
            if (!resultSet.next()) {
                return id;
            } else {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static int hasResidenceInDB(int civilianId, TypeOfResidence typeOfResidence){
        int id = 0;
        String querySelect = "SELECT * FROM passportService.residence WHERE (civilian = '" + civilianId + "' AND " +
                "type_r = '" +  typeOfResidence.getOrdinalNumber() + "');";
        try {
            statement.executeQuery(querySelect);
            resultSet = statement.getResultSet();
            if (!resultSet.next()) {
                return id;
            } else {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static int hasCivlianInDB(String FIO){
        int id = 0;
        String querySelect = "SELECT * FROM passportService.civilian WHERE (FIO = '" + FIO + "');";
        try {
            statement.executeQuery(querySelect);
            resultSet = statement.getResultSet();
            if (!resultSet.next()) {
                return id;
            } else {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static void updateAmountOfDuty(String amount, int oldAmount) {
        String queryUpdate = "UPDATE amount_of_duty SET amount = '" + amount + "' where amount = '" + oldAmount + "';";
        try {
            statement.executeUpdate(queryUpdate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public static void checkFinishedPassport(){
//        String querySelect = "SELECT * FROM passportService.registration_passport WHERE (passport = '" + residenceApplication.getPassport() + "' AND" +
//                " type_r = '" + residenceApplication.getTypeOfResidence() + "');";
//        try {
//            statement.executeQuery(querySelect);
//            resultSet = statement.getResultSet();
//            if (!resultSet.next()) {
//                String queryInsert = "INSERT INTO passportService.residence_application (passport, type_r, address) \n" +
//                        " VALUES ('" + residenceApplication.getPassport() + "', '" + residenceApplication.getTypeOfResidence().getOrdinalNumber() + "', '" +
//                        residenceApplication.getAddress() + "');";
//                statement.executeUpdate(queryInsert);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

}
