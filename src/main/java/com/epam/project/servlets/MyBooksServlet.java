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
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "myBooks", urlPatterns = "/myBooks")
public class MyBooksServlet extends HttpServlet {
    private final int SUBS_PER_PAGE=20;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        req.setAttribute("counts",SubscriptionServiceImpl.getInstance().countForUser(Arrays.asList(Status.ROOM, Status.SUBSCRIPTION),(Long) session.getAttribute("user_id"))/ SUBS_PER_PAGE +1);
        String page= req.getParameter("page") ==null ? "1" :req.getParameter("page") ;
        req.setAttribute("subscriptions", SubscriptionServiceImpl.getInstance().findSubscriptionsByBookStatusesAndUser(Arrays.asList(Status.SUBSCRIPTION, Status.ROOM), (Long) session.getAttribute("user_id"),SUBS_PER_PAGE,Integer.parseInt(page)));
        req.setAttribute("today", new Date(System.currentTimeMillis()));
        RequestDispatcher view = req.getRequestDispatcher("jsp/myBooks.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("renew".equals(req.getParameter("action")))
            BookServiceImpl.getInstance().updateBookStatus(Status.RENEW, Long.valueOf(req.getParameter("id")));
        else {
            if (Status.ROOM.equals(Status.valueOf(req.getParameter("status"))))
                BookServiceImpl.getInstance().updateBookStatus(Status.RETURNING_ROOM, Long.valueOf(req.getParameter("id")));
            else
                BookServiceImpl.getInstance().updateBookStatus(Status.RETURNING_SUBSCRIPTION, Long.valueOf(req.getParameter("id")));
        }
        doGet(req, resp);
    }
}
