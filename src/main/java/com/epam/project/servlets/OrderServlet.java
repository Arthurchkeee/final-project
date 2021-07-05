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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name="order",urlPatterns = "/order")
public class OrderServlet extends HttpServlet {
    SubscriptionService service=SubscriptionServiceImpl.getInstance();
    BookService bookService=BookServiceImpl.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");
        String status=req.getParameter("status");
        HttpSession session= req.getSession();
        Long user= (Long) session.getAttribute("user_id");
        if(status.equals("SUBSCRIBE")) {
            service.orderSubscription(user, Long.valueOf(id), new Date(System.currentTimeMillis()));
            bookService.orderBook(Long.valueOf(id));
        }
        else {
            service.orderRoom(user, Long.valueOf(id));
            bookService.updateBookStatus(Status.ORDERED_ROOM, Long.valueOf(id));
        }
        RequestDispatcher view= req.getRequestDispatcher("/catalog");
        view.forward(req,resp);
    }
}
