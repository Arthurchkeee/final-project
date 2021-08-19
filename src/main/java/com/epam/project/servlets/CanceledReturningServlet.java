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
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "canceledReturning", urlPatterns = "/canceledReturning")
public class CanceledReturningServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<Subscription> subscriptionList = SubscriptionServiceImpl.getInstance().findSubscriptionsByBookStatusesAndUser(Arrays.asList(Status.RETURNING_SUBSCRIPTION, Status.RETURNING_ROOM), (Long) session.getAttribute("user_id"));
        req.setAttribute("subscriptions", subscriptionList);
        RequestDispatcher view = req.getRequestDispatcher("jsp/returns.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Status.RETURNING_ROOM.equals(Status.valueOf(req.getParameter("status"))))
            BookServiceImpl.getInstance().updateBookStatus(Status.ROOM, Long.valueOf(req.getParameter("book_id")));
        else
            BookServiceImpl.getInstance().updateBookStatus(Status.SUBSCRIPTION, Long.valueOf(req.getParameter("book_id")));
        doGet(req, resp);
    }
}
