package com.movie.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.moviedb.db.DBConnectionManager;
import com.moviedb.entity.MovieAward;
import com.moviedb.entity.PersonAward;

public class AwardsModel {
    public static ArrayList<PersonAward> getCelebAwardsList(long celebID) throws SQLException {
        
        PreparedStatement statement = DBConnectionManager.getConnection()
                .prepareStatement(
                        "SELECT awardid FROM PERSONAWARD WHERE personid = "
                                + celebID);

        ResultSet resultSet = statement.executeQuery();

        ArrayList<PersonAward> celebAwards = new ArrayList<>();

        while (resultSet.next())
            celebAwards.add(new PersonAward(resultSet.getLong(1)));
        
        return celebAwards;
    }
    
    public static ArrayList<MovieAward> getMovieAwardsList(long movieID) throws SQLException {
        
        PreparedStatement statement = DBConnectionManager.getConnection()
                .prepareStatement(
                        "SELECT awardid FROM MOVIEAWARD WHERE movieid = "
                                + movieID);

        ResultSet resultSet = statement.executeQuery();

        ArrayList<MovieAward> movieAwards = new ArrayList<>();

        while (resultSet.next())
            movieAwards.add(new MovieAward(resultSet.getLong(1)));
        
        return movieAwards;
    }
}
