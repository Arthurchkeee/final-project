package com.epam.project.servlets;

import com.epam.project.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="users",urlPatterns = "/users")
public class UserTableServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("counts", UserServiceImpl.getInstance().count()/20+1);
        String page= req.getParameter("page") ==null ? "1" :req.getParameter("page") ;
        req.setAttribute("users", UserServiceImpl.getInstance().selectSomeUsers(20,Integer.parseInt(page)));
        RequestDispatcher view=req.getRequestDispatcher("jsp/users.jsp");
        view.forward(req,resp);
    }
}
