package com.movie.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.moviedb.db.DBConnectionManager;
import com.moviedb.entity.UserReview;

public class ReviewModel {

    public static ArrayList<UserReview> getUserReviews(long movieID)
            throws SQLException {
        PreparedStatement statement = DBConnectionManager.getConnection()
                .prepareStatement(
                        "SELECT reviewid FROM USERREVIEW WHERE movieid = "
                                + movieID);

        ResultSet resultSet = statement.executeQuery();

        ArrayList<UserReview> userReviews = new ArrayList<>();

        while (resultSet.next())
            userReviews.add(new UserReview(resultSet.getLong(1)));

        return userReviews;
    }

    public static void submitUserReview(long userID, long movieID, String review)
            throws SQLException {

        PreparedStatement statement = DBConnectionManager.getConnection()
                .prepareStatement("INSERT INTO USERREVIEW VALUES (?, ?, ?, ?)");
        
        long randomReviewID = RandomIDGenerator.getRandomAvailableID("USERREVIEW", "reviewid");
        
        statement.setLong(1, randomReviewID);
        statement.setLong(2, movieID);
        statement.setLong(3, userID);
        statement.setString(4, review);
        
        statement.executeUpdate();
    }
}
