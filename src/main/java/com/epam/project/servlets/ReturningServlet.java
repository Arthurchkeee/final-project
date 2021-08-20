package com.epam.project.servlets;

import com.epam.project.entities.Status;
import com.epam.project.service.impl.BookServiceImpl;
import com.epam.project.service.impl.SubscriptionServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "returning", urlPatterns = "/returning")
public class ReturningServlet extends HttpServlet {
    private static final int SUBS_PER_PAGE =20;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("count",SubscriptionServiceImpl.getInstance().count(Arrays.asList(Status.RETURNING_ROOM, Status.RETURNING_SUBSCRIPTION))/SUBS_PER_PAGE+1);
        String page= req.getParameter("page") ==null ? "1" :req.getParameter("page") ;
        req.setAttribute("subscriptions", SubscriptionServiceImpl.getInstance().findSubscriptionsByBookStatuses(Arrays.asList(Status.RETURNING_ROOM, Status.RETURNING_SUBSCRIPTION),SUBS_PER_PAGE, Integer.parseInt(page)));
        RequestDispatcher view = req.getRequestDispatcher("jsp/returningBook.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("cancel".equals(req.getParameter("action"))) {
            if (Status.RETURNING_ROOM.equals(Status.valueOf(req.getParameter("status"))))
                BookServiceImpl.getInstance().updateBookStatus(Status.ROOM, Long.valueOf(req.getParameter("book_id")));
            else
                BookServiceImpl.getInstance().updateBookStatus(Status.SUBSCRIPTION, Long.valueOf(req.getParameter("book_id")));
        } else {
            SubscriptionServiceImpl.getInstance().deleteSubscription(Long.valueOf(req.getParameter("id")), Long.valueOf(req.getParameter("book_id")));
            BookServiceImpl.getInstance().updateBookStatus(Status.FREE, Long.valueOf(req.getParameter("book_id")));
        }
        doGet(req, resp);
    }
}
