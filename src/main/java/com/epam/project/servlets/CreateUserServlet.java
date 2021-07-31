package com.epam.project.servlets;

import com.epam.project.entities.Role;
import com.epam.project.entities.User;
import com.epam.project.service.impl.UserServiceImpl;
import com.epam.project.validator.LoginValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "createUser",urlPatterns = "/createUser")
public class CreateUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(LoginValidator.getInstance().isValid(req.getParameter("name"),req.getParameter("password"))) {
            User user=new User(null,req.getParameter("name"),req.getParameter("password"), Role.valueOf(req.getParameter("role")));
        UserServiceImpl.getInstance().create(user);
        }
        resp.sendRedirect(req.getContextPath()+"/createUser");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/createUser.jsp").forward(req, resp);
    }
}
