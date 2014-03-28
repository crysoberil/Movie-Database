package com.movie.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.moviedb.db.DBConnectionManager;
import com.moviedb.entity.MovieInfo;

public class WatchListModel {
    private ArrayList<String> movieNames;
    private ArrayList<String> movieLinks;

    public WatchListModel(long userID) throws SQLException {
        PreparedStatement statement = DBConnectionManager.getConnection()
                .prepareStatement(
                        "SELECT movieid FROM WISHTOWATCH WHERE usrid = "
                                + userID);

        ResultSet resultSet = statement.executeQuery();

        movieNames = new ArrayList<>();
        movieLinks = new ArrayList<>();

        while (resultSet.next()) {
            movieLinks.add(MovieInfo.getMovieLink(resultSet.getLong(1)));
            movieNames.add(MovieInfo.getMovieName(resultSet.getLong(1)));
        }
    }
    
    public ArrayList<String> getMovieNames() {
        return movieNames;
    }
    
    public ArrayList<String> getMovieLinks() {
        return movieLinks;
    }
}
