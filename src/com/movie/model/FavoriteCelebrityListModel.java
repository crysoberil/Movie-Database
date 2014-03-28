package com.movie.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.moviedb.db.DBConnectionManager;
import com.moviedb.entity.AddedCelebListElement;
import com.moviedb.entity.AddedMovieListElement;

public class FavoriteCelebrityListModel {

    public static ArrayList<AddedCelebListElement> getFavoriteCelebList(
            long userID) throws SQLException {

        PreparedStatement statement = DBConnectionManager.getConnection()
                .prepareStatement(
                        "SELECT personid FROM FAVORITECELEB WHERE usrid = "
                                + userID);

        ResultSet resultSet = statement.executeQuery();

        ArrayList<AddedCelebListElement> favoriteCelebsList = new ArrayList<>();

        while (resultSet.next())
            favoriteCelebsList.add(new AddedCelebListElement(resultSet
                    .getLong(1)));

        return favoriteCelebsList;
    }

    public static boolean isInFavoriteCelebList(long userID, long celebID)
            throws SQLException {

        String sqlString = "SELECT id FROM FAVORITECELEB WHERE personID = "
                + celebID + " AND usrid = " + userID;

        PreparedStatement statement = DBConnectionManager.getConnection()
                .prepareStatement(sqlString);

        ResultSet resultSet = statement.executeQuery();

        return resultSet.next();
    }

    public static void removeFromFavoriteCelebList(long userID, long celebID)
            throws SQLException {
        PreparedStatement statement = DBConnectionManager.getConnection()
                .prepareStatement(
                        "DELETE FROM FAVORITECELEB WHERE usrid =" + userID
                                + " AND personid = " + celebID);
        statement.executeUpdate();
    }

    public static void addCelebToFavoriteList(long userID, long celebID)
            throws SQLException {
        
        long favoriteListElementID = RandomIDGenerator.getRandomAvailableID(
                "FAVORITECELEB", "id");

        PreparedStatement statement = DBConnectionManager.getConnection()
                .prepareStatement("INSERT INTO FAVORITECELEB VALUES (?, ?, ?)");

        statement.setLong(1, favoriteListElementID);
        statement.setLong(2, userID);
        statement.setLong(3, celebID);

        statement.execute();
    }
}
