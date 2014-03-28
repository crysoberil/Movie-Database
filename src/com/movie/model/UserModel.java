package com.movie.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.moviedb.db.DBConnectionManager;

public class UserModel {

    public static Long getUserId(String userName, String password)
            throws SQLException {

        PreparedStatement statement = DBConnectionManager
                .getConnection()
                .prepareStatement(
                        "SELECT usrid FROM USRINFO WHERE lower(username) = ? AND password = ?");

        statement.setString(1, userName);
        statement.setString(2, password);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next())
            return resultSet.getLong(1);

        return null;

    }

    public static boolean createNewUser(String userName, String name,
            String password, Date dob, String sex, String email)
            throws SQLException {

        long userID = RandomIDGenerator
                .getRandomAvailableID("USRINFO", "usrid");

        String sqlString = "INSERT INTO USRINFO VALUES(?, ?, ?, ?, ?, ?, ?, sysdate)";

        PreparedStatement statement = DBConnectionManager.getConnection()
                .prepareStatement(sqlString);

        statement.setLong(1, userID);
        statement.setString(2, userName);
        statement.setString(3, name);
        statement.setString(4, email);
        statement.setString(5, password);
        statement.setDate(6, new java.sql.Date(dob.getTime()));
        statement.setString(7, sex);

        return statement.executeUpdate() == 1;
    }

    public static boolean emailAvailableForNewUser(String email) {

        if (email == null || !validEmailAddress(email))
            return false;

        try {
            PreparedStatement statement = DBConnectionManager.getConnection()
                    .prepareStatement(
                            "SELECT usrid FROM USRINFO WHERE emailid = ?");

            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return false;

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean validEmailAddress(String email) {
        return email.toLowerCase().matches("[a-z0-9_.]+@[a-z0-9_]+.[a-z0-9_]+");
    }

    public static boolean userNameAvailable(String userName) {

        if (userName == null || userName.length() < 5)
            return false;

        try {

            PreparedStatement statement = DBConnectionManager.getConnection()
                    .prepareStatement(
                            "SELECT usrid FROM USRINFO WHERE username = ?");
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();

            return !resultSet.next();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
