package com.movie.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movie.model.FavoriteCelebrityListModel;
import com.moviedb.db.DBConnectionManager;
import com.moviedb.entity.AddedCelebListElement;
import com.moviedb.entity.AddedMovieListElement;

public class LoadFavoriteCelebrityController extends CheckedHttpServlet {

    @Override
    public void checkedDoGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (!isLoggedIn(req.getSession()))
            SimpleFeedbackPageLoader.showSimpleFeedbackPage(req, resp, "Error",
                    "Operation failed", "Please log in to use this feature");
        else {
            try {

                long userID = (Long) req.getAttribute("userid");

                ArrayList<AddedCelebListElement> favoriteCelebsList = FavoriteCelebrityListModel
                        .getFavoriteCelebList(userID);

                req.setAttribute("favoritecelebslist", favoriteCelebsList);

                // TODO dispatch
                req.getRequestDispatcher("/WEB-INF/showfavoritecelebs.jsp")
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
