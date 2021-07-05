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
@WebServlet(name = "returning",urlPatterns = "/returning")
public class ReturningServlet extends HttpServlet {
    SubscriptionService service=SubscriptionServiceImpl.getInstance();
    BookService bookService=BookServiceImpl.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("subscriptions",service.findAllSubscriptions());
        RequestDispatcher view= req.getRequestDispatcher("jsp/returningBook.jsp");
        view.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");
        String book_id=req.getParameter("book_id");
        service.deleteSubscription(Long.valueOf(id),Long.valueOf(book_id));
        bookService.updateBookStatus(Status.FREE,Long.valueOf(book_id));
        doGet(req, resp);
    }
}
