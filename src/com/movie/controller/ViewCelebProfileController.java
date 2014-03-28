package com.movie.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movie.model.CelebrityModel;
import com.moviedb.db.DBConnectionManager;
import com.moviedb.entity.CelebrityInfo;

public class ViewCelebProfileController extends CheckedHttpServlet {

    @Override
    public void checkedDoGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {

            long celebID = Long.parseLong(req.getParameter("celebid"));

            CelebrityInfo celebrityInfo = new CelebrityInfo(celebID);
            req.setAttribute("celebinfo", celebrityInfo);

            if (isLoggedIn(req.getSession())) {
                long userID = (Long) req.getAttribute("userid");

                boolean isFavoriteListed = CelebrityModel.isFavoriteListed(
                        celebID, userID);
                req.setAttribute("isfavoritelisted", isFavoriteListed);
            }

            req.getRequestDispatcher("/WEB-INF/showcelebprofile.jsp").forward(
                    req, resp);

        } catch (SQLException ex) {
            System.err.println("failed to load celeb info");
            ex.printStackTrace();
            SimpleFeedbackPageLoader.showSimpleFeedbackPage(req, resp, "Error",
                    "Invalid Request", "No such celebrity");
        }
    }

    @Override
    public void checkedDoPost(HttpServletRequest req, HttpServletResponse resp) {
        // TODO Auto-generated method stub

    }

}
