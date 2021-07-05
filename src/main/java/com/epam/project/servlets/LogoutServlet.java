package com.epam.project.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(name="logoutUser",urlPatterns = "/LogoutUser")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out=resp.getWriter();

        req.getRequestDispatcher("jsp/login.jsp").include(req, resp);

        req.getSession(false).invalidate();
        req.getSession(true);
        out.print("You are successfully logged out!");
        out.close();

    }
}
