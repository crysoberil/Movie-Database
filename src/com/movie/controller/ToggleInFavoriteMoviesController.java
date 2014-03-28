package com.movie.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movie.model.MovieListModel;
import com.movie.model.MovieModel;
import com.movie.model.RandomIDGenerator;
import com.moviedb.db.DBConnectionManager;

public class ToggleInFavoriteMoviesController extends CheckedHttpServlet {

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
                long movieID = Long.parseLong(req.getParameter("movieid"));
                long userID = (Long) req.getAttribute("userid");

                if (MovieListModel.movieInFavoriteList(movieID, userID)) {
                    MovieListModel.removeMovieFromFavoriteList(movieID, userID);

                    SimpleFeedbackPageLoader.showSimpleFeedbackPage(req, resp,
                            "Removed from favorite list", "Removed",
                            "The movie has been removed from your favoritelist");
                } else {
                    MovieListModel.addMovieToFavoriteList(movieID, userID);
                    
                    SimpleFeedbackPageLoader.showSimpleFeedbackPage(req, resp,
                            "Added to favorite list", "Movie added",
                            "The movie has been added to your favorite list");
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
