package com.moviedb.entity;

import java.sql.SQLException;

public abstract class Review {
    protected long movieID;
    protected String movieName;
    protected String review;
    protected long reviewID;

    public long getMovieID() {
        return movieID;
    }

    public String getMovieTitle() {
        return movieName;
    }

    public String getReview() {
        return review;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovie(long movieID) {
        this.movieID = movieID;

        try {
            this.movieName = MovieInfo.getMovieName(movieID);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getMovieLink() {
        return MovieInfo.getMovieLink(movieID);
    }

    public long getReviewID() {
        return reviewID;
    }

    public abstract String getReviewLink();
}
