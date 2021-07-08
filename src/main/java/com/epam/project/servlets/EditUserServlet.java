package com.epam.project.servlets;

import com.epam.project.entities.*;
import com.epam.project.service.impl.BookServiceImpl;
import com.epam.project.service.impl.UserServiceImpl;
import com.epam.project.validator.LoginValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "editUser", urlPatterns = "/editUser")
public class EditUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("id",req.getParameter("id"));
        req.setAttribute("name",req.getParameter("name"));
        RequestDispatcher view=req.getRequestDispatcher("jsp/editUser.jsp");
        view.forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(LoginValidator.getInstance().isValidate(req.getParameter("user_name"),req.getParameter("password")))
        UserServiceImpl.getInstance().update(new User(Long.valueOf(req.getParameter("id")),req.getParameter("user_name"),req.getParameter("password"), Role.valueOf(req.getParameter("role"))));
        RequestDispatcher view=req.getRequestDispatcher("/users");
        view.forward(req,resp);
    }
}
