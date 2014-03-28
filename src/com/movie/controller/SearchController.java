package com.movie.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movie.model.CelebrityModel;
import com.movie.model.SearchModel;
import com.moviedb.db.DBConnectionManager;
import com.moviedb.entity.CelebrityInfo;
import com.moviedb.entity.MovieInfo;

public class SearchController extends CheckedHttpServlet {

    @Override
    public void checkedDoGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String searchWord = req.getParameter("searchword").trim()
                    .toLowerCase();

            if (searchWord.length() == 0)
                throw new IllegalArgumentException();

            SearchModel searchModel = new SearchModel(searchWord);

            req.setAttribute("celeblinks", searchModel.getCelebLinks());
            req.setAttribute("celebnames", searchModel.getCelebNames());
            req.setAttribute("movielinks", searchModel.getMovieLinks());
            req.setAttribute("movietitles", searchModel.getMovieTitles());

            // TODO dispatch
            req.getRequestDispatcher("/WEB-INF/showsearchresults.jsp").forward(
                    req, resp);

        } catch (Exception ex) {
            ex.printStackTrace();

            SimpleFeedbackPageLoader.showSimpleFeedbackPage(req, resp, "Error",
                    "Invalid search", "Search parameter invalid");
        }
    }

    @Override
    public void checkedDoPost(HttpServletRequest req, HttpServletResponse resp) {
        // TODO Auto-generated method stub
        // Not supported in the system
    }

}
