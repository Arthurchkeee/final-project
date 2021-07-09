package com.epam.project.servlets;

import com.epam.project.entities.Status;
import com.epam.project.entities.Subscription;
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
import java.util.List;

@WebServlet(name="myBooks",urlPatterns = "/myBooks")
public class MyBooksServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session= req.getSession();
        List<Subscription> subscriptionList=  SubscriptionServiceImpl.getInstance().findAllSubscriptionsByUser((Long) session.getAttribute("user_id"));
        req.setAttribute("subscriptions",subscriptionList);
        RequestDispatcher view= req.getRequestDispatcher("jsp/myBooks.jsp");
        view.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(Status.ROOM.equals(Status.valueOf(req.getParameter("status"))))
            BookServiceImpl.getInstance().updateBookStatus(Status.RETURNING_ROOM,Long.valueOf(req.getParameter("id")));
        else
            BookServiceImpl.getInstance().updateBookStatus(Status.RETURNING_SUBSCRIPTION,Long.valueOf(req.getParameter("id")));
        doGet(req, resp);
    }
}
