package com.movie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movie.model.ReviewModel;

public class SubmitReviewController extends CheckedHttpServlet {

    @Override
    public void checkedDoGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        SimpleFeedbackPageLoader.showSimpleFeedbackPage(req, resp, "Error",
                "Invalid request", "Press the back button");
    }

    @Override
    public void checkedDoPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (!isLoggedIn(req.getSession()))
            SimpleFeedbackPageLoader.showSimpleFeedbackPage(req, resp, "Error",
                    "Please log in", "Please log in to use this feature");
        else {
            try {
                long userID = (long) req.getAttribute("userid");
                long movieID = Long.parseLong(req.getParameter("movieid"));
                String reviewString = req.getParameter("reviewstring").trim();

                if (reviewString.length() == 0)
                    throw new IllegalArgumentException();

                ReviewModel.submitUserReview(userID, movieID, reviewString);

                SimpleFeedbackPageLoader.showSimpleFeedbackPage(req, resp,
                        "Success", "Review posted",
                        "Your review has been postde");

            } catch (Exception ex) {
                ex.printStackTrace();
                SimpleFeedbackPageLoader.showSimpleFeedbackPage(req, resp,
                        "Error", "Invalid request",
                        "Your review could not be posted");
            }
        }
    }

}
