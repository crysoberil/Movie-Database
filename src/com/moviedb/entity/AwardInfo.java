package com.moviedb.entity;

public abstract class AwardInfo {
    protected long awardID;
    protected String awardName;
    protected int awardYear;
    protected String awardCategory;

    public long getAwardID() {
        return awardID;
    }

    protected void setAwardID(long awardID) {
        this.awardID = awardID;
    }

    public String getAwardName() {
        return awardName;
    }

    protected void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public int getAwardYear() {
        return awardYear;
    }

    protected void setAwardYear(int awardYear) {
        this.awardYear = awardYear;
    }

    public String getAwardCategory() {
        return awardCategory;
    }

    protected void setAwardCategory(String awardCategory) {
        this.awardCategory = awardCategory;
    }

    public abstract String getAwardLink();
}
