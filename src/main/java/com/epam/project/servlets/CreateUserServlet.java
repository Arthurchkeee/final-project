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
    UserService userService=new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user=new User(null,req.getParameter("name"),req.getParameter("password"), Role.valueOf(req.getParameter("role")));
        RequestDispatcher view= req.getRequestDispatcher("WEB-INF/jsp/createUser.jsp");
        view.forward(req,resp);
        PrintWriter out=resp.getWriter();
        if(userService.create(user)){
            out.print("User added successfully");
        }
        else
            out.print("Check your input data!");
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/createUser.jsp").forward(req, resp);
    }
}
