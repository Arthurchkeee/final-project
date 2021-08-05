package com.epam.project.servlets;

import com.epam.project.entities.User;
import com.epam.project.service.impl.UserServiceImpl;
import com.epam.project.validator.LoginValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="loginUser",urlPatterns = "/LoginUser")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("login");
        String password=req.getParameter("password");
        if(LoginValidator.getInstance().isValid(username,password) && UserServiceImpl.getInstance().getAccess(username,password)){
            User user = UserServiceImpl.getInstance().findUserByLogin(username);
            HttpSession session=req.getSession();
            session.setAttribute("username",username);
            session.setAttribute("user_id",user.getId());
            session.setAttribute("role",user.getRole());
            RequestDispatcher view= req.getRequestDispatcher("jsp/start.jsp");
            view.forward(req,resp);
        }
        else{
            PrintWriter out=resp.getWriter();
            req.getRequestDispatcher("jsp/login.jsp").include(req,resp);
            out.print("Your login or password are wrong!");
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/login.jsp").include(req,resp);
    }
}
