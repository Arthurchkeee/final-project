package com.epam.project.servlets;


import com.epam.project.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="deleteUser",urlPatterns = "/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserServiceImpl.getInstance().delete(Long.valueOf(req.getParameter("id")));
        RequestDispatcher view= req.getRequestDispatcher("/users");
        view.forward(req,resp);
    }
}
