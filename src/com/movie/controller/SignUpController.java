package com.movie.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movie.model.RandomIDGenerator;
import com.movie.model.UserModel;
import com.moviedb.db.DBConnectionManager;

public class SignUpController extends CheckedHttpServlet {

    @Override
    public void checkedDoGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        // Not supported
    }

    @Override
    public void checkedDoPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (isLoggedIn(req.getSession()))
            forceLogOut(req, resp);

        String userName = req.getParameter("username");
        String name = req.getParameter("name");
        // String name = "Jisan Mahmud";
        String password = req.getParameter("password");
        String password2 = req.getParameter("password2");
        String email = req.getParameter("email");
        Date dob = parseDateString(req.getParameter("dob"));
        String sex = req.getParameter("sex");

        if (email != null)
            email = email.toLowerCase();

        if (!UserModel.userNameAvailable(userName)) {
            SimpleFeedbackPageLoader
                    .showSimpleFeedbackPage(
                            req,
                            resp,
                            "Error",
                            "Username unavailable",
                            "Sorry. This username is already taken or invalid. Please choose something else");

        } else if (!passwordIsInCorrectFormat(password)) {

            SimpleFeedbackPageLoader
                    .showSimpleFeedbackPage(req, resp, "Error",
                            "Password too short",
                            "Password too short. Please choose a password of at least length 5");

        } else if (!passwordsMatch(password, password2)) {
            SimpleFeedbackPageLoader.showSimpleFeedbackPage(req, resp, "Error",
                    "Password mismatch",
                    "Passwords didn't match. Feel up again");
        } else if (!UserModel.emailAvailableForNewUser(email)) {

            SimpleFeedbackPageLoader.showSimpleFeedbackPage(req, resp, "Error",
                    "Email already used",
                    "This email is invalid or have already been used");
        } else if (!dateOfBirthIsValid(dob)) {

            SimpleFeedbackPageLoader.showSimpleFeedbackPage(req, resp, "Error",
                    "Invalid date of birth",
                    "Date of birth year should be between 1900 and 2006");
        } else {

            try {
                if (UserModel.createNewUser(userName, name, password, dob, sex,
                        email)) {
                    SimpleFeedbackPageLoader
                            .showSimpleFeedbackPage(req, resp,
                                    "Account created", "Account created",
                                    "Your account has been created. Please log in to continue");
                } else
                    throw new SQLException();
            } catch (SQLException ex) {
                SimpleFeedbackPageLoader
                        .showSimpleFeedbackPage(req, resp, "Error",
                                "Account creation failed",
                                "Sorry. Your account could not be created. Please try again later.");
            }

        }
    }

    private boolean passwordsMatch(String password, String password2) {
        return password2 != null && password.equals(password2);
    }

    private Date parseDateString(String dateString) {

        DateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    private boolean dateOfBirthIsValid(Date dob) {

        if (dob == null)
            return false;

        return true;
    }

    private boolean passwordIsInCorrectFormat(String password) {
        return password.length() >= 5;
    }
}
