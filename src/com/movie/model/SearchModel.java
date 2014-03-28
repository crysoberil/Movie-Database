package com.movie.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.moviedb.db.DBConnectionManager;
import com.moviedb.entity.MovieInfo;

public class SearchModel {

    private String searchWord;
    private ArrayList<String> movieNames;
    private ArrayList<String> movieLinks;
    private ArrayList<String> celebNames;
    private ArrayList<String> celebLinks;

    public SearchModel(String searchWord) throws SQLException {
        this.searchWord = searchWord;

        setMovieList();
        setCelebList();
    }

    private void setCelebList() throws SQLException {
        
        celebLinks = new ArrayList<>();
        celebNames = new ArrayList<>();

        PreparedStatement preparedStatement = DBConnectionManager
                .getConnection().prepareStatement(
                        "SELECT personid, name FROM MOVIEPEOPLE WHERE lower(name) LIKE "
                                + "\'%%" + searchWord + "%%\'");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            celebLinks.add(CelebrityModel.getCelebrityProfileLink(resultSet
                    .getLong(1)));
            celebNames.add(resultSet.getString(2));
        }
    }

    private void setMovieList() throws SQLException {
        
        movieLinks = new ArrayList<>();
        movieNames = new ArrayList<>();
        
        PreparedStatement preparedStatement = DBConnectionManager
                .getConnection().prepareStatement(
                        "SELECT movieid, title FROM MOVIE WHERE lower(title) LIKE "
                                + "\'%%" + searchWord + "%%\'");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            movieLinks.add(MovieInfo.getMovieLink(resultSet.getLong(1)));
            movieNames.add(resultSet.getString(2));
        }
    }

    public ArrayList<String> getMovieTitles() {
        return movieNames;
    }

    public ArrayList<String> getMovieLinks() {
        return movieLinks;
    }

    public ArrayList<String> getCelebNames() {
        return celebNames;
    }

    public ArrayList<String> getCelebLinks() {
        return celebLinks;
    }
}
