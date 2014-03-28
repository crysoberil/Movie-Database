package com.moviedb.entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.movie.model.CelebrityModel;
import com.moviedb.db.DBConnectionManager;

public class PersonAward extends AwardInfo {

    protected long celebID;
    protected String celebName;

    public PersonAward(long awardID) throws SQLException {
        setAwardID(awardID);
        initParams(awardID);
    }

    private void initParams(long awardID) throws SQLException {
        String sqlString = "SELECT awardname, year, category, personid FROM AWARDS, PERSONAWARD WHERE AWARDS.awardid = PERSONAWARD.awardid AND AWARDS.awardid = "
                + awardID;

        PreparedStatement statement = DBConnectionManager.getConnection()
                .prepareStatement(sqlString);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {

            setAwardCategory(resultSet.getString(3));
            setAwardName(resultSet.getString(1));
            setAwardYear(resultSet.getInt(2));
            setPerson(resultSet.getLong(4));

        } else
            throw new SQLException();
    }

    private void setPerson(long personID) throws SQLException {
        this.celebID = personID;
        this.celebName = CelebrityModel.getCelebrityName(personID);
    }

    public long getCelebID() {
        return celebID;
    }

    public String getCelebName() {
        return celebName;
    }

    public String getCelebProfileLink() {
        return CelebrityModel.getCelebrityProfileLink(celebID);
    }

    @Override
    public String getAwardLink() {
        return CelebrityModel.getAwardLink(celebID);
    }

}
