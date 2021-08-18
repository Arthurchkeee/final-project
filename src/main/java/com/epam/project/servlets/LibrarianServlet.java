package com.epam.project.servlets;

import com.epam.project.entities.Status;
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
import java.io.IOException;
import java.sql.Date;

@WebServlet(name="librarian",urlPatterns = "/librarian")
public class LibrarianServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("renew".equals(req.getParameter("action"))){
            SubscriptionServiceImpl.getInstance().renewSubscription(Long.parseLong(req.getParameter("id")),new Date(System.currentTimeMillis()+31l*24l*60l*60l*1000l));
            BookServiceImpl.getInstance().updateBookStatus(Status.SUBSCRIPTION,Long.valueOf(req.getParameter("book_id")));
        }
        else if("delete".equals(req.getParameter("action"))){
            SubscriptionServiceImpl.getInstance().delete(Long.valueOf(req.getParameter("id")));
            BookServiceImpl.getInstance().updateBookStatus(Status.FREE,Long.valueOf(req.getParameter("book_id")));
        }else {
            if (Status.ORDERED_SUBSCRIPTION.equals(Status.valueOf(req.getParameter("status"))))
                BookServiceImpl.getInstance().updateBookStatus(Status.SUBSCRIPTION, Long.valueOf(req.getParameter("book_id")));
            else
                BookServiceImpl.getInstance().updateBookStatus(Status.ROOM, Long.valueOf(req.getParameter("book_id")));
        }
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setAttribute("subscriptions",SubscriptionServiceImpl.getInstance().findSubscriptionsBy3BookStatus(Status.ORDERED_ROOM,Status.ORDERED_SUBSCRIPTION,Status.RENEW));
        RequestDispatcher view= req.getRequestDispatcher("jsp/librarian.jsp");
        view.forward(req,resp);
    }
}
