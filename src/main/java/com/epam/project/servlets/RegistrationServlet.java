package com.epam.project.servlets;

import com.epam.project.entities.Role;
import com.epam.project.entities.User;
import com.epam.project.service.impl.UserServiceImpl;
import com.epam.project.validator.LoginValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "registration", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/registrationForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (LoginValidator.getInstance().isValid(req.getParameter("name"), req.getParameter("password"))) {
            User user = new User(null, req.getParameter("name"), req.getParameter("password"), Role.READER);
            UserServiceImpl.getInstance().create(user);
        }
        resp.sendRedirect(req.getContextPath() + "/LoginUser");
    }
}
