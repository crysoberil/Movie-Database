package com.moviedb.entity;

public class StudioInfo {
    public static String getStudioLink(long studioID) {
        return "/Movie_Database/studio?studioid=" + studioID;
    }
}
