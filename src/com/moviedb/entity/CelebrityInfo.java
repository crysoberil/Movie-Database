package com.moviedb.entity;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.movie.model.CelebrityModel;
import com.moviedb.db.DBConnectionManager;

public class CelebrityInfo {
    private String name;
    private Date dob;
    private String sex;
    private String country;
    private String bio;
    private long personID;
    
    public CelebrityInfo(long celebID) throws SQLException {
        Statement statement = DBConnectionManager.getConnection()
                .createStatement();

        ResultSet resultSet = statement
                .executeQuery("SELECT * FROM MOVIEPEOPLE WHERE personid = "
                        + celebID);

        if (resultSet.next())
            init(resultSet);
        else
            throw new SQLException();
    }

    public void init(long personID, String name, Date dob, String sex,
            String country, String bio) {

        this.personID = personID;
        this.name = name;
        this.dob = dob;
        this.sex = sex;
        this.country = country;
        this.bio = bio;
    }

    public void init(ResultSet resultSet) throws SQLException {

        init(resultSet.getLong(6), resultSet.getString("name"), resultSet
                .getDate(2), resultSet.getString("sex"), resultSet
                .getString("countryfrom"), resultSet.getString("shortbio"));
    }

    public String getCelebrityAwardLink() {
        return CelebrityModel.getCelebrityAwardsLink(personID);
    }

    public String getCelebrityProfileLink() {
        return CelebrityModel.getCelebrityProfileLink(personID);
    }

    public long getCelebID() {
        return personID;
    }

    public String getName() {
        return name;
    }

    public Date getDateOfBirth() {
        return dob;
    }

    public String getCelebSex() {
        return sex;
    }

    public String getCoutry() {
        return country;
    }

    public String getShortBio() {
        return bio;
    }
}
