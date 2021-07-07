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

@WebServlet(name="librarian",urlPatterns = "/librarian")
public class LibrarianServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(Status.ORDERED_SUBSCRIPTION.equals(req.getParameter("status")))
            BookServiceImpl.getInstance().updateBookStatus(Status.SUBSCRIPTION,Long.valueOf(req.getParameter("id")));
        else
            BookServiceImpl.getInstance().updateBookStatus(Status.ROOM,Long.valueOf(req.getParameter("id")));
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setAttribute("subscriptions",SubscriptionServiceImpl.getInstance().findAllSubscriptions());
        RequestDispatcher view= req.getRequestDispatcher("jsp/librarian.jsp");
        view.forward(req,resp);
    }
}
