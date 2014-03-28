package com.movie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

public abstract class CheckedHttpServlet extends HttpServlet {

    private static final long serialVersionUID = -2885065854765509135L;

    @Override
    protected final void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        forceNoCache(resp);

        loadLogInInfoInRequest(req);
        checkedDoGet(req, resp);
    }

    private void forceNoCache(HttpServletResponse resp) {
        // Set standard HTTP/1.1 no-cache headers.
        resp.setHeader("Cache-Control",
                "private, no-store, no-cache, must-revalidate");

        // Set standard HTTP/1.0 no-cache header.
        resp.setHeader("Pragma", "no-cache");

        resp.setDateHeader("Expires", 0);
    }

    @Override
    protected final void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        forceNoCache(resp);
        loadLogInInfoInRequest(req);
        checkedDoPost(req, resp);
    }

    private void loadLogInInfoInRequest(HttpServletRequest req) {

        HttpSession session = req.getSession();

        loadLoginStatusInSession(req);

        req.setAttribute("loggedin", session.getAttribute("loggedin"));
        req.setAttribute("userid", session.getAttribute("userid"));
        req.setAttribute("username", session.getAttribute("username"));
    }

    private void loadLoginStatusInSession(HttpServletRequest req) {

        HttpSession session = req.getSession();

        if (session.getAttribute("loggedin") == null) // session contains no
                                                      // info on login
            loadLogInInfoToSessionFromCookies(req);
    }

    private void loadLogInInfoToSessionFromCookies(HttpServletRequest req) {

        long userID = 0;
        String userName = null;

        Cookie[] cookies = req.getCookies();

        if (cookies != null)
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userid"))
                    userID = Long.parseLong(cookie.getValue());
                else if (cookie.getName().equals("username"))
                    userName = cookie.getValue();
            }

        HttpSession session = req.getSession();

        session.setAttribute("username", userName);
        session.setAttribute("userid", userID);

        if (userName == null)
            session.setAttribute("loggedin", false);
        else
            session.setAttribute("loggedin", true);
    }

    protected boolean isLoggedIn(HttpSession session) {
        return (Boolean) session.getAttribute("loggedin");
    }

    protected void forceLogOut(HttpServletRequest req, HttpServletResponse resp) {

        req.setAttribute("loggedin", false);
        
        req.removeAttribute("userid");
        req.removeAttribute("username");

        HttpSession session = req.getSession();

        session.removeAttribute("loggedin");
        session.removeAttribute("userid");
        session.removeAttribute("username");

        Cookie[] cookies = req.getCookies();

        if (cookies != null)
            for (int i = 0; i < cookies.length; i++) {
                cookies[i].setValue(null);
                cookies[i].setPath(null);
                cookies[i].setMaxAge(0);
                resp.addCookie(cookies[i]);
            }
    }

    protected String getUserName(HttpSession session) {
        return (String) session.getAttribute("username");
    }

    protected long getUserID(HttpSession session) {
        return (Long) session.getAttribute("userid");
    }

    public abstract void checkedDoGet(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException;

    public abstract void checkedDoPost(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException;;

}
