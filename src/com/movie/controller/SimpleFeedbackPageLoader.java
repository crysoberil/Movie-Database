package com.movie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleFeedbackPageLoader {
    public static void showSimpleFeedbackPage(HttpServletRequest req,
            HttpServletResponse resp, String title, String header, String body) {

        req.setAttribute("title", title);
        req.setAttribute("header", header);
        req.setAttribute("body", body);

        try {
            req.getRequestDispatcher("/WEB-INF/simple_feedback.jsp").forward(
                    req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
