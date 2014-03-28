package com.movie.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.moviedb.db.DBConnectionManager;
import com.moviedb.entity.CriticReview;
import com.moviedb.entity.MovieInfo;

public class MovieModel {
    public static MovieInfo getLatestMovie() throws SQLException {
        
        PreparedStatement queryStatement = DBConnectionManager
                .getConnection()
                .prepareStatement(
                        "SELECT * FROM MOVIE WHERE releaseyear >= any(SELECT releaseyear FROM MOVIE)");
        ResultSet resultSet = queryStatement.executeQuery();
        resultSet.next(); // never false

        return new MovieInfo(resultSet.getLong(1));
    }
    
    public static ArrayList<CriticReview> getCriticReviews(long movieID) throws SQLException {
        
        PreparedStatement statement = DBConnectionManager.getConnection()
                .prepareStatement(
                        "SELECT criticreviewid FROM CRITICREVIEW WHERE movieid = "
                                + movieID);

        ResultSet resultSet = statement.executeQuery();

        ArrayList<CriticReview> criticReviews = new ArrayList<>();

        while (resultSet.next())
            criticReviews.add(new CriticReview(resultSet.getLong(1)));
        
        return criticReviews;
    }
}
