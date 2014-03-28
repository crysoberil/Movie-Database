package com.movie.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movie.model.FavoriteCelebrityListModel;
import com.movie.model.RandomIDGenerator;
import com.moviedb.db.DBConnectionManager;

public class ToggleInFavoteCelebListController extends CheckedHttpServlet {
    @Override
    public void checkedDoGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {

            boolean isLoggedIn = isLoggedIn(req.getSession());

            if (!isLoggedIn)
                SimpleFeedbackPageLoader.showSimpleFeedbackPage(req, resp,
                        "Please log in", "Operation failed",
                        "Please log in to use this feature");
            else {
                long celebID = Long.parseLong(req.getParameter("celebid"));
                long userID = (Long) req.getAttribute("userid");
                

                if (FavoriteCelebrityListModel.isInFavoriteCelebList(userID, celebID)) {
                    FavoriteCelebrityListModel.removeFromFavoriteCelebList(userID, celebID);

                    SimpleFeedbackPageLoader.showSimpleFeedbackPage(req, resp,
                            "Removed from watchlist", "Removed",
                            "The celebrity has been removed from your favorite list");
                } else {
                    FavoriteCelebrityListModel.addCelebToFavoriteList(userID, celebID);

                    SimpleFeedbackPageLoader.showSimpleFeedbackPage(req, resp,
                            "Added to favoritelist", "Celebrity added",
                            "The celebrity has been added to your favorite list");
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            SimpleFeedbackPageLoader.showSimpleFeedbackPage(req, resp, "Error",
                    "Operation failed", "Please try again later");
        }

    }

    @Override
    public void checkedDoPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // TODO Auto-generated method stub

    }
}
