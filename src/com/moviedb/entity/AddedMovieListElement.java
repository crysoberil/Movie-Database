package com.moviedb.entity;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;

public class AddedMovieListElement {
    private long movieID;
    private String movieName;
    private Date dateEntered;

    public AddedMovieListElement(long movieID, Date downloadDate)
            throws SQLException {

        setMovie(movieID);
        this.dateEntered = downloadDate;

    }

    private void setMovie(long movieID) throws SQLException {
        this.movieID = movieID;
        this.movieName = MovieInfo.getMovieName(movieID);
    }

    public long getMovieID() {
        return movieID;
    }

    public String getMovieName() {
        return movieName;
    }

    public Date getAddedDate() {
        return dateEntered;
    }

    public String getMovieLink() {
        return MovieInfo.getMovieLink(movieID);
    }
}
