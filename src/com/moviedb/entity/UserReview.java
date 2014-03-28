package com.moviedb.entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.moviedb.db.DBConnectionManager;

public class UserReview extends Review {

    private long userID;
    private String name;

    public UserReview(long reviewID) throws SQLException {

        super.reviewID = reviewID;

        setParams(reviewID);
    }

    private void setParams(long reviewID) throws SQLException {
        String sqlString = "SELECT movieid, usrid, review FROM USERREVIEW WHERE reviewid = "
                + reviewID;

        PreparedStatement statement = DBConnectionManager.getConnection()
                .prepareStatement(sqlString);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            setMovie(resultSet.getLong("movieid"));
            userID = resultSet.getLong(2);
            setReview(resultSet.getString(3));

            setReviewerName();

        } else
            throw new SQLException();
    }

    private void setReviewerName() throws SQLException {
        String sqlString = "SELECT name FROM USRINFO WHERE usrid = " + userID;

        PreparedStatement statement = DBConnectionManager.getConnection()
                .prepareStatement(sqlString);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next())
            name = resultSet.getString(1);
        else
            throw new SQLException();
    }

    public String getReviewerName() {
        return name;
    }

    public static String getReviewLink(long reviewID) {
        return "/Movie_Database/userreview?reviewid=" + reviewID;
    }

    @Override
    public String getReviewLink() {
        return getReviewLink(reviewID);
    }

}
