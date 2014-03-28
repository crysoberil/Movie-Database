package com.moviedb.entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.moviedb.db.DBConnectionManager;

public class CriticReview extends Review {

    private long criticID;
    private String criticName;
    private String criticCompany;
    private long reviewID;

    public CriticReview(long reviewID) throws SQLException {

        super.reviewID = reviewID;

        setParams(reviewID);
    }

    private void setParams(long reviewID) throws SQLException {
        String sqlString = "SELECT modieid, criticid, review, criticname, company FROM CRITICREVIEW, CRITICS WHERE criticreviewID = "
                + reviewID + " AND CRITICREVIEW.criticid = CRITICS.criticid";

        PreparedStatement statement = DBConnectionManager.getConnection()
                .prepareStatement(sqlString);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            setMovie(resultSet.getLong(1));
            criticID = resultSet.getLong(2);
            setReview(resultSet.getString(3));
            criticName = resultSet.getString(4);
            criticCompany = resultSet.getString(5);

        } else
            throw new SQLException();
    }

    public String getCriticName() {
        return criticName;
    }

    public long getCriticID() {
        return criticID;
    }

    public String getCriticCompany() {
        return criticCompany;
    }

    public static String getReviewLink(long reviewID) {
        return "/Movie_Database/criticreview?reviewid=" + reviewID;
    }

    @Override
    public String getReviewLink() {
        return getReviewLink(reviewID);
    }

}
