package com.moviedb.entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.moviedb.db.DBConnectionManager;

public class MovieAward extends AwardInfo {

    protected long movieID;
    protected String movieTitle;

    public MovieAward(long awardID) throws SQLException {
        setAwardID(awardID);

        initParams(awardID);
    }

    private void initParams(long awardID) throws SQLException {
        String sqlString = "SELECT awardname, year, category, movieid FROM AWARDS, MOVIEAWARD WHERE AWARDS.awardid = MOVIEAWARD.awardid AND AWARDS.awardid = "
                + awardID;

        PreparedStatement statement = DBConnectionManager.getConnection()
                .prepareStatement(sqlString);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {

            setAwardCategory(resultSet.getString(3));
            setAwardName(resultSet.getString(1));
            setAwardYear(resultSet.getInt(2));
            setMovie(resultSet.getLong(4));

        } else
            throw new SQLException();
    }

    private void setMovie(long movieID) throws SQLException {
        this.movieID = movieID;
        this.movieTitle = MovieInfo.getMovieName(movieID);
    }

    public long getMovieID() {
        return movieID;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getMovieLink() {
        return MovieInfo.getMovieLink(movieID);
    }

    public static String getAwardLink(long awardID) {
        return "/Movie_Database/movieaward?awardid=" + awardID;
    }

    @Override
    public String getAwardLink() {
        return getAwardLink(awardID);
    }
    
    @Override
    public String toString() {
        return String.format("Movie : <a href=\"%s\">%s</a>\n\n%s, %s, %s\n\n\n", getMovieLink(), getMovieTitle(), getAwardName(), getAwardCategory(), getAwardYear());
    }
}
