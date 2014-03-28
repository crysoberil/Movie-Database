package com.movie.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movie.model.AwardsModel;
import com.moviedb.db.DBConnectionManager;
import com.moviedb.entity.AwardInfo;
import com.moviedb.entity.MovieAward;
import com.moviedb.entity.MovieInfo;

public class MovieAwardsLoader extends CheckedHttpServlet {

    @Override
    public void checkedDoGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {

            long movieid = Long.parseLong(req.getParameter("movieid"));

            ArrayList<MovieAward> movieAwards = AwardsModel
                    .getMovieAwardsList(movieid);

            req.setAttribute("movieawards", movieAwards);
            req.setAttribute("movieid", movieid);
            req.setAttribute("moviename", MovieInfo.getMovieName(movieid));

            // TODO dispatch request
            req.getRequestDispatcher("/WEB-INF/showmovieaward.jsp").forward(
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
