package com.movie.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.moviedb.db.DBConnectionManager;
import com.moviedb.entity.MovieInfo;

public class ViewMovieController extends CheckedHttpServlet {

    @Override
    public void checkedDoGet(HttpServletRequest req, HttpServletResponse resp) {

        // System.out.println("Reached here");

        try {

            String num = req.getParameter("movieid");

            long movieID = Long.parseLong(num);

            MovieInfo movieInfo = new MovieInfo(movieID);
            req.setAttribute("movieinfo", movieInfo);

            if (isLoggedIn(req.getSession())) {

                long userID = (Long) req.getAttribute("userid");

                req.setAttribute("iswatchlisted",
                        MovieInfo.movieIsWatchListed(movieID, userID));

                req.setAttribute("isfavoritelisted",
                        MovieInfo.movieIsFavoriteListed(movieID, userID));
            }

            req.getRequestDispatcher("/WEB-INF/showmovie.jsp").forward(req,
                    resp);
        } catch (SQLException | ServletException | IOException
                | NullPointerException | NumberFormatException e) {
            e.printStackTrace();
            SimpleFeedbackPageLoader.showSimpleFeedbackPage(req, resp, "Error",
                    "Invalid link", "Not a valid movie link");
        }
    }

    @Override
    public void checkedDoPost(HttpServletRequest req, HttpServletResponse resp) {
        // TODO Auto-generated method stub
        // Not supported in the system
    }

}
