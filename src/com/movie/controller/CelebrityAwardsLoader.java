package com.movie.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.Cleaner;

import com.movie.model.AwardsModel;
import com.movie.model.CelebrityModel;
import com.moviedb.db.DBConnectionManager;
import com.moviedb.entity.CelebrityInfo;
import com.moviedb.entity.MovieAward;
import com.moviedb.entity.PersonAward;

public class CelebrityAwardsLoader extends CheckedHttpServlet {

    @Override
    public void checkedDoGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {

            long celebid = Long.parseLong(req.getParameter("celebid"));

            ArrayList<PersonAward> celebAwards = AwardsModel
                    .getCelebAwardsList(celebid);

            req.setAttribute("celebawards", celebAwards);
            
            req.setAttribute("celebname",
                    CelebrityModel.getCelebrityName(celebid));
            
            req.setAttribute("celebid", celebid);

            // TODO dispatch request
            req.getRequestDispatcher("/WEB-INF/showcelebawards.jsp").forward(
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
