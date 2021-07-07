package com.epam.project.servlets;

import com.epam.project.entities.Role;
import com.epam.project.entities.User;
import com.epam.project.service.UserService;
import com.epam.project.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "createUser",urlPatterns = "/createUser")
public class CreateUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user=new User(null,req.getParameter("name"),req.getParameter("password"), Role.valueOf(req.getParameter("role")));
        UserServiceImpl.getInstance().create(user);
        RequestDispatcher view= req.getRequestDispatcher("jsp/createUser.jsp");
        view.forward(req,resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/createUser.jsp").forward(req, resp);
    }
}
