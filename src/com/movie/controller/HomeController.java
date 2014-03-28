package com.movie.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movie.model.AwardsModel;
import com.movie.model.CelebrityModel;
import com.movie.model.MovieModel;
import com.moviedb.db.DBConnectionManager;
import com.moviedb.entity.CelebrityInfo;
import com.moviedb.entity.MovieInfo;

public class HomeController extends CheckedHttpServlet {

    @Override
    public void checkedDoGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        MovieInfo movieInfo = getMovieSuggestion();
        CelebrityInfo celebrityInfo = getCelebSuggestion();

        req.setAttribute("moviesuggestion", movieInfo);
        req.setAttribute("celebsuggestion", celebrityInfo);

        RequestDispatcher dispatcher = req
                .getRequestDispatcher("/WEB-INF/home.jsp");

        dispatcher.forward(req, resp);
    }

    private CelebrityInfo getCelebSuggestion() {
        try {
            return CelebrityModel.getCelebritySuggestion();

        } catch (SQLException e) {
            System.err.println("failed to build preparedstatement");
            e.printStackTrace();
        }

        return null;
    }

    private MovieInfo getMovieSuggestion() {
        try {
            return MovieModel.getLatestMovie();

        } catch (SQLException e) {
            System.err.println("failed to build preparedstatement");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void checkedDoPost(HttpServletRequest req, HttpServletResponse resp) {
        // TODO Auto-generated method stub
        // Not supported in the system
    }

}
