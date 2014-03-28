package com.movie.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movie.model.WatchListModel;
import com.moviedb.db.DBConnectionManager;
import com.moviedb.entity.MovieInfo;

public class LoadWatchListController extends CheckedHttpServlet {

    private static final long serialVersionUID = -3003466410446353402L;

    @Override
    public void checkedDoGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (!isLoggedIn(req.getSession()))
            SimpleFeedbackPageLoader.showSimpleFeedbackPage(req, resp, "Error",
                    "Operation failed", "Please log in to use this feature");
        else {
            try {
                long userID = (Long) req.getAttribute("userid");

                WatchListModel watchList = new WatchListModel(userID);

                req.setAttribute("movienames", watchList.getMovieNames());
                req.setAttribute("movielinks", watchList.getMovieLinks());

                // TODO dispatch
                req.getRequestDispatcher("/WEB-INF/checkwatchlist.jsp")
                        .forward(req, resp);

            } catch (Exception ex) {
                ex.printStackTrace();
                SimpleFeedbackPageLoader.showSimpleFeedbackPage(req, resp,
                        "Error", "Operation failed", "Please try again later");
            }
        }

    }

    @Override
    public void checkedDoPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // TODO Auto-generated method stub

    }

}
