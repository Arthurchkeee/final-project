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
    SubscriptionService service=new SubscriptionServiceImpl();
    BookService bookService=new BookServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");
        bookService.updateBookStatus(Status.SUBSCRIPTION,Long.valueOf(id));
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setAttribute("subscriptions",service.findAllBookByStatus(Status.ORDERED_SUBSCRIPTION));
        RequestDispatcher view= req.getRequestDispatcher("WEB-INF/jsp/librarian.jsp");
        view.forward(req,resp);
    }
}
