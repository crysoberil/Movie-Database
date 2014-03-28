package com.movie.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.moviedb.db.DBConnectionManager;
import com.moviedb.entity.AddedMovieListElement;

public class MovieListModel {

    public static ArrayList<AddedMovieListElement> getDownloadedMoviesList(
            long userID) throws SQLException {

        PreparedStatement statement = DBConnectionManager.getConnection()
                .prepareStatement(
                        "SELECT movieid, downloaddate FROM DOWNLOADMOVIE WHERE usrid = "
                                + userID);

        ResultSet resultSet = statement.executeQuery();

        ArrayList<AddedMovieListElement> downloadedMoviesList = new ArrayList<>();

        while (resultSet.next())
            downloadedMoviesList.add(new AddedMovieListElement(resultSet
                    .getLong(1), resultSet.getDate(2)));

        return downloadedMoviesList;
    }

    public static ArrayList<AddedMovieListElement> getFavoriteMoviesList(
            long userID) throws SQLException {
        PreparedStatement statement = DBConnectionManager.getConnection()
                .prepareStatement(
                        "SELECT movieid, dateadded FROM FAVORITEMOVIE WHERE usrid = "
                                + userID);

        ResultSet resultSet = statement.executeQuery();

        ArrayList<AddedMovieListElement> favoriteMovieList = new ArrayList<>();

        while (resultSet.next())
            favoriteMovieList.add(new AddedMovieListElement(resultSet
                    .getLong(1), resultSet.getDate(2)));

        return favoriteMovieList;
    }

    public static boolean movieInFavoriteList(long moviID, long userID)
            throws SQLException {
        
        String sqlString = "SELECT id FROM FAVORITEMOVIE WHERE movieid = "
                + moviID + " AND usrid = " + userID;

        PreparedStatement statement = DBConnectionManager.getConnection()
                .prepareStatement(sqlString);

        ResultSet resultSet = statement.executeQuery();

        return resultSet.next();
    }

    public static void removeMovieFromFavoriteList(long movieID, long userID)
            throws SQLException {
        PreparedStatement statement = DBConnectionManager.getConnection()
                .prepareStatement(
                        "DELETE FROM FAVORITEMOVIE WHERE usrid =" + userID
                                + " AND movieid = " + movieID);
        statement.executeUpdate();
    }

    public static void addMovieToFavoriteList(long movieID, long userID)
            throws SQLException {
        long favoriteListElementID = RandomIDGenerator.getRandomAvailableID(
                "FAVORITEMOVIE", "id");

        PreparedStatement statement = DBConnectionManager.getConnection()
                .prepareStatement(
                        "INSERT INTO FAVORITEMOVIE VALUES (?, ?, ?, sysdate)");

        statement.setLong(1, favoriteListElementID);
        statement.setLong(2, userID);
        statement.setLong(3, movieID);

        statement.execute();
    }

    public static boolean isWatchListed(long movieID, long userID)
            throws SQLException {

        String sqlString = "SELECT wishtowatchid FROM WISHTOWATCH WHERE movieid = "
                + movieID + " AND usrid = " + userID;

        PreparedStatement statement = DBConnectionManager.getConnection()
                .prepareStatement(sqlString);

        ResultSet resultSet = statement.executeQuery();

        return resultSet.next();
    }

    public static void removeMovieFromWacthList(long movieID, long userID)
            throws SQLException {
        PreparedStatement statement = DBConnectionManager.getConnection()
                .prepareStatement(
                        "DELETE FROM WISHTOWATCH WHERE usrid =" + userID
                                + " AND movieid = " + movieID);
        statement.executeUpdate();
    }

    public static void addMovieToWatchList(long movieID, long userID)
            throws SQLException {

        long wishlistElementID = RandomIDGenerator.getRandomAvailableID(
                "WISHTOWATCH", "wishtowatchid");

        PreparedStatement statement = DBConnectionManager.getConnection()
                .prepareStatement("INSERT INTO WISHTOWATCH VALUES (?, ?, ?)");

        statement.setLong(1, wishlistElementID);
        statement.setLong(2, userID);
        statement.setLong(3, movieID);

        statement.execute();
    }
}
