package com.epam.project.servlets;

import com.epam.project.service.BookService;
import com.epam.project.service.SubscriptionService;
import com.epam.project.service.impl.BookServiceImpl;
import com.epam.project.service.impl.SubscriptionServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name="order",urlPatterns = "/order")
public class OrderServlet extends HttpServlet {
    SubscriptionService service=new SubscriptionServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");
        HttpSession session= req.getSession();
        Long user= (Long) session.getAttribute("user_id");
        service.orderSubscription(user,Long.valueOf(id),new Date(System.currentTimeMillis()));
        RequestDispatcher view= req.getRequestDispatcher("/catalog");
        view.forward(req,resp);
    }
}
