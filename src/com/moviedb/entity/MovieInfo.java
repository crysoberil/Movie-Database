package com.moviedb.entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.movie.model.CelebrityModel;
import com.moviedb.db.DBConnectionManager;

public class MovieInfo {

    private long movieID;
    private String title;
    private int releaseYear;
    private String country;
    private String plot;
    private int length;
    private String language;

    private Object trailer;
    private Object movieFile;

    private long studioID;

    private String studioName;
    private ArrayList<String> actorsName;
    private ArrayList<String> directorsName;
    private ArrayList<String> producersName;
    private ArrayList<String> genreNames;

    private String studioLink;
    private ArrayList<String> actorsLink;
    private ArrayList<String> directorsLink;
    private ArrayList<String> producersLink;

    private double rating;

    public MovieInfo(long movieID) throws SQLException {

        Statement statement = DBConnectionManager.getConnection()
                .createStatement();

        ResultSet resultSet = statement
                .executeQuery("SELECT * FROM MOVIE WHERE movieid = " + movieID);

        if (resultSet.next())
            init(resultSet);
        else
            throw new SQLException();
    }

    public void init(ResultSet resultSet) throws NumberFormatException,
            SQLException {
        init(Long.parseLong(resultSet.getString("movieid")),
                resultSet.getString("title"),
                Integer.parseInt(resultSet.getString("releaseyear")),
                resultSet.getString("country"), resultSet.getString("plot"),
                Integer.parseInt(resultSet.getString("length")),
                resultSet.getString("language"),
                Long.parseLong(resultSet.getString("studioid")),
                getRating(Long.parseLong(resultSet.getString("movieid"))));

    }

    void init(long movieID, String title, int releaseYear, String country,
            String plot, int length, String language, long studioID,
            double rating) {

        this.title = title;
        this.movieID = movieID;
        this.releaseYear = releaseYear;
        this.country = country;
        this.plot = plot;
        this.length = length;
        this.language = language;
        this.studioID = studioID;

        setRating(rating);
        setStudio();
        setActors();
        setDirectors();
        setProducers();
        setGenres();
    }

    public String getStudioName() {
        return studioName;
    }

    public String getStudioLink() {
        return studioLink;
    }

    public ArrayList<String> getActorsName() {
        return actorsName;
    }

    public ArrayList<String> getActorsLink() {
        return actorsLink;
    }

    public ArrayList<String> getProducersName() {
        return producersName;
    }

    public ArrayList<String> getProducersLink() {
        return producersLink;
    }

    public ArrayList<String> getDirectorsName() {
        return directorsName;
    }

    public ArrayList<String> getDirectorsLink() {
        return directorsLink;
    }

    public String getMovieLink() {
        return getMovieLink(movieID);
    }

    public String getUserReviewsLink() {
        return getUserReviewsLink(movieID);
    }

    public static String getUserReviewsLink(long movieID) {
        return "/Movie_Database/userreviews?movieid=" + movieID;
    }

    public String getCriticReviewsLink() {
        return getCriticReviewsLink(movieID);
    }

    public static String getCriticReviewsLink(long movieID) {
        return "/Movie_Database/criticreviews?movieid=" + movieID;
    }

    public static String getMovieLink(long movieID) {
        return "/Movie_Database/movie?movieid=" + movieID;
    }

    public ArrayList<String> getGenreList() {
        return genreNames;
    }

    public String getGenresAsString() {
        StringBuilder genres = new StringBuilder();

        for (int i = 0; i < genreNames.size(); i++) {
            if (i != 0)
                genres.append(", ");

            genres.append(genreNames.get(i));
        }

        return genres.toString();
    }

    private void setGenres() {
        genreNames = new ArrayList<>();

        try {
            Statement statement = DBConnectionManager.getConnection()
                    .createStatement();

            String queryString = String
                    .format("SELECT genrename FROM GENRE WHERE id IN (SELECT DISTINCT genreid FROM MOVIEGENRE WHERE movieid = %d)",
                            movieID);

            ResultSet resultSet = statement.executeQuery(queryString);

            while (resultSet.next())
                genreNames.add(resultSet.getString(1));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void setDirectors() {
        directorsLink = new ArrayList<>();
        directorsName = new ArrayList<>();

        try {
            Statement statement = DBConnectionManager.getConnection()
                    .createStatement();

            String queryString = String
                    .format("SELECT personid, name FROM MOVIEPEOPLE WHERE personid IN (SELECT DISTINCT personid FROM DIRECTEDBY WHERE movieid = %d)",
                            movieID);

            ResultSet resultSet = statement.executeQuery(queryString);

            while (resultSet.next()) {
                directorsName.add(resultSet.getString(2));
                directorsLink.add(CelebrityModel
                        .getCelebrityProfileLink(resultSet.getLong(1)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void setProducers() {
        producersLink = new ArrayList<>();
        producersName = new ArrayList<>();

        try {
            Statement statement = DBConnectionManager.getConnection()
                    .createStatement();

            String queryString = String
                    .format("SELECT personid, name FROM MOVIEPEOPLE WHERE personid IN (SELECT DISTINCT personid FROM PRODUCEDBY WHERE movieid = %d)",
                            movieID);

            ResultSet resultSet = statement.executeQuery(queryString);

            while (resultSet.next()) {
                producersName.add(resultSet.getString(2));
                producersLink.add(CelebrityModel
                        .getCelebrityProfileLink(resultSet.getLong(1)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void setActors() {
        actorsLink = new ArrayList<>();
        actorsName = new ArrayList<>();

        try {
            Statement statement = DBConnectionManager.getConnection()
                    .createStatement();

            String queryString = String
                    .format("SELECT personid, name FROM MOVIEPEOPLE WHERE personid IN (SELECT DISTINCT personid FROM STAROF WHERE movieid = %d)",
                            movieID);

            ResultSet resultSet = statement.executeQuery(queryString);

            while (resultSet.next()) {
                actorsName.add(resultSet.getString(2));
                actorsLink.add(CelebrityModel.getCelebrityProfileLink(resultSet
                        .getLong(1)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void setStudio() {

        try {
            Statement statement = DBConnectionManager.getConnection()
                    .createStatement();

            ResultSet resultSet = statement
                    .executeQuery("SELECT studioname FROM STUDIO WHERE studioid = "
                            + studioID);

            if (resultSet.next()) {
                studioName = resultSet.getString(1);
                studioLink = StudioInfo.getStudioLink(studioID);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static double getRating(long movieID) {

        try {
            String sqlString = "SELECT avg(rate) FROM USERRATING WHERE movieid = ?";

            PreparedStatement statement = DBConnectionManager.getConnection()
                    .prepareStatement(sqlString);

            statement.setLong(1, movieID);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return resultSet.getDouble(1);

            throw new SQLException();

        } catch (SQLException ex) {
            ex.printStackTrace();

            return 0; // not yet rated
        }
    }

    public static String getMovieName(long movieID) throws SQLException {
        PreparedStatement statement = DBConnectionManager.getConnection()
                .prepareStatement(
                        "SELECT title FROM MOVIE WHERE movieid = " + movieID);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next())
            return resultSet.getString(1);

        return null;
    }

    private void setRating(double rating) {
        this.rating = rating;
    }

    public String getAwardsLink() {
        return getAwardsLink(movieID);
    }

    public static String getAwardsLink(long movieID) {
        return "/Movie_Database/movieawards?movieid=" + movieID;
    }

    public double getRating() {
        return rating;
    }

    public long getMovieID() {
        return movieID;
    }

    public String getMovieTitle() {
        return title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getCountry() {
        return country;
    }

    public String getPlot() {
        return plot;
    }

    public int getLength() {
        return length;
    }

    public String getLanguage() {
        return language;
    }

    public long getStudioID() {
        return studioID;
    }

    public static boolean movieIsWatchListed(long movieID, long userID)
            throws SQLException {
        PreparedStatement statement = DBConnectionManager.getConnection()
                .prepareStatement(
                        "SELECT wishtowatchid FROM WISHTOWATCH WHERE usrid = "
                                + userID + " AND movieid = " + movieID);

        ResultSet resultSet = statement.executeQuery();

        return resultSet.next();
    }

    public static boolean movieIsFavoriteListed(long movieID, long userID)
            throws SQLException {
        
        PreparedStatement statement = DBConnectionManager.getConnection()
                .prepareStatement(
                        "SELECT id FROM FAVORITEMOVIE WHERE usrid = " + userID
                                + " AND movieid = " + movieID);

        ResultSet resultSet = statement.executeQuery();

        return resultSet.next();
    }
}