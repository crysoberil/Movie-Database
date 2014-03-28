package com.movie.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.movie.model.UserModel;
import com.moviedb.db.DBConnectionManager;

public class LogInController extends CheckedHttpServlet {

    @Override
    public void checkedDoGet(HttpServletRequest req, HttpServletResponse resp) {
        // TODO Auto-generated method stub
        // Impossible case scenerio
    }

    @Override
    public void checkedDoPost(HttpServletRequest req, HttpServletResponse resp) {

        logIn(req, resp);
    }

    private void logIn(HttpServletRequest req, HttpServletResponse resp) {
        if (isLoggedIn(req.getSession()))
            forceLogOut(req, resp);

        String userName = req.getParameter("username").toLowerCase();
        String password = req.getParameter("password").toLowerCase();

        Long userID = null;

        try {
            userID = UserModel.getUserId(userName, password);

        } catch (SQLException e1) {
            System.err.println("could not log in");
            e1.printStackTrace();
        }

        if (userID == null) {
            SimpleFeedbackPageLoader.showSimpleFeedbackPage(req, resp, "Error",
                    "User not found", "Username or password is wrong");
        } else {

            resp.addCookie(new Cookie("userid", userID.toString()));
            resp.addCookie(new Cookie("username", userName));

            HttpSession session = req.getSession();

            session.setAttribute("loggedin", Boolean.TRUE);
            session.setAttribute("username", userName);
            session.setAttribute("userid", userID);

            req.setAttribute("loggedin", Boolean.TRUE);

            try {
                resp.sendRedirect("/Movie_Database/");
            } catch (IOException e) {
                System.err.println("could no redirect");
                e.printStackTrace();
            }
        }
    }

}
