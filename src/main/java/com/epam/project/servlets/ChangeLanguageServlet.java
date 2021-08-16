package com.epam.project.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet(name="changeLocale",urlPatterns = "/changeLocale")
public class ChangeLanguageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String lang = req.getParameter("lang");
        session.setAttribute("locale",lang);
        if(req.getSession().getAttribute("username")==null) {
            RequestDispatcher view = req.getRequestDispatcher("jsp/login.jsp");
            view.forward(req,resp);
        }else{
        RequestDispatcher view= req.getRequestDispatcher("jsp/start.jsp");
            view.forward(req,resp);
        }
    }
}
