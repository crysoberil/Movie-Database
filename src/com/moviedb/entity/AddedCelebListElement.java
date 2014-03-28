package com.moviedb.entity;

import java.sql.SQLException;

import com.movie.model.CelebrityModel;

public class AddedCelebListElement {
    private long celebID;
    private String celebName;
    
    public AddedCelebListElement(long celebID) throws SQLException {
        
        this.celebID = celebID;
        celebName = CelebrityModel.getCelebrityName(celebID);
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
}
