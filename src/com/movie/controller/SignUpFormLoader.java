package com.movie.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpFormLoader extends CheckedHttpServlet {

    @Override
    public void checkedDoGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        forceLogOut(req, resp);

        req.getRequestDispatcher("/WEB-INF/Sign Up.html").forward(req, resp);

        // if (isLoggedIn(req.getSession()))
        // resp.sendRedirect("/Movie_Database");
        // else {
        // req.getRequestDispatcher("/WEB-INF/Sign Up.html")
        // .forward(req, resp);
        // }
    }

    @Override
    public void checkedDoPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        // We don't support this
    }

}
