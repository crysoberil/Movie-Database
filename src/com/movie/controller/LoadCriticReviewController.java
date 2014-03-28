package com.movie.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movie.model.MovieModel;
import com.moviedb.db.DBConnectionManager;
import com.moviedb.entity.CriticReview;
import com.moviedb.entity.MovieInfo;
import com.moviedb.entity.UserReview;

public class LoadCriticReviewController extends CheckedHttpServlet {

    @Override
    public void checkedDoGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {

            long movieid = Long.parseLong(req.getParameter("movieid"));

            ArrayList<CriticReview> criticReviews = MovieModel
                    .getCriticReviews(movieid);

            req.setAttribute("criticreviews", criticReviews);
            req.setAttribute("movieid", movieid);
            req.setAttribute("moviename", MovieInfo.getMovieName(movieid));

            // TODO dispatch request
            req.getRequestDispatcher("/WEB-INF/showcriticreview.jsp").forward(
                    req, resp);

        } catch (Exception ex) {
            ex.printStackTrace();
            SimpleFeedbackPageLoader
                    .showSimpleFeedbackPage(req, resp, "Error", "Unavailable",
                            "Invalid request or resource is unavailable");
        }

    }

    @Override
    public void checkedDoPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // TODO Auto-generated method stub

    }

}
