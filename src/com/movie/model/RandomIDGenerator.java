package com.movie.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import com.moviedb.db.DBConnectionManager;

public class RandomIDGenerator {

    public static long getRandomAvailableID(String tableName, String idField) {

        while (true) {
            try {
                String sqlString = "SELECT max(" + idField + ") FROM " + tableName;

                PreparedStatement statement = DBConnectionManager
                        .getConnection().prepareStatement(sqlString);
                
                ResultSet resultSet = statement.executeQuery();
                
                if (resultSet.next())
                    return resultSet.getLong(1) + 1;
            } catch (SQLException ex) {

            }
        }
    }
}
