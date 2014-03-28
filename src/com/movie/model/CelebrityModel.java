package com.movie.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.moviedb.db.DBConnectionManager;
import com.moviedb.entity.CelebrityInfo;

public class CelebrityModel {
    public static CelebrityInfo getCelebritySuggestion() throws SQLException {

        PreparedStatement queryStatement = DBConnectionManager
                .getConnection()
                .prepareStatement(
                        "SELECT * FROM (SELECT personid FROM MOVIEPEOPLE ORDER BY DBMS_RANDOM.RANDOM) WHERE rownum < 2");
        ResultSet resultSet = queryStatement.executeQuery();
        resultSet.next(); // never false

        return new CelebrityInfo(resultSet.getLong("personid"));
    }

    public static String getCelebrityAwardsLink(long celebID) {
        return "/Movie_Database/celebawards?celebid=" + celebID;
    }

    public static String getCelebrityProfileLink(long celebID) {
        return "/Movie_Database/celebrity?celebid=" + celebID;
    }

    public static String getCelebrityName(long celebID) throws SQLException {
        PreparedStatement statement = DBConnectionManager.getConnection()
                .prepareStatement(
                        "SELECT name FROM MOVIEPEOPLE WHERE personid = "
                                + celebID);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next())
            return resultSet.getString(1);

        return null;
    }

    public static String getAwardLink(long awardID) {
        return "/Movie_Database/personaward?awardid=" + awardID;
    }

    public static boolean isFavoriteListed(long celebID, long userID)
            throws SQLException {

        PreparedStatement statement = DBConnectionManager.getConnection()
                .prepareStatement(
                        "SELECT personid FROM FAVORITECELEB WHERE usrid = "
                                + userID + " AND personid = " + celebID);
        
        ResultSet resultSet = statement.executeQuery();
        
        return resultSet.next();
    }
}
