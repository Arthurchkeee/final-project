package com.epam.project.servlets;

import com.epam.project.entities.Book;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

@WebServlet(name="order",urlPatterns = "/order")
public class OrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");
       Book book= BookServiceImpl.getInstance().findBookById(Long.valueOf(id));
       req.setAttribute("id",id);
       req.setAttribute("name",book.getName());
       req.setAttribute("author",book.getAuthor());
       req.setAttribute("status",book.getStatus());
       req.setAttribute("genre",book.getGenre());
       req.setAttribute("description",book.getDescription());
       req.setAttribute("image",book.getImage());
        RequestDispatcher view= req.getRequestDispatcher("/jsp/bookProfile.jsp");
        view.forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");
        String status=req.getParameter("status");
        HttpSession session= req.getSession();
        Long user= (Long) session.getAttribute("user_id");
        if(status.equals("SUBSCRIBE")) {
            SubscriptionServiceImpl.getInstance().orderSubscription(user, Long.valueOf(id), new Date(System.currentTimeMillis()+31l*24l*60l*60l*1000l));
            BookServiceImpl.getInstance().updateBookStatus(Status.ORDERED_SUBSCRIPTION,Long.valueOf(id));
        }
        else {
            SubscriptionServiceImpl.getInstance().orderRoom(user, Long.valueOf(id));
            BookServiceImpl.getInstance().updateBookStatus(Status.ORDERED_ROOM, Long.valueOf(id));
        }
        RequestDispatcher view=req.getRequestDispatcher("/catalog");
        view.forward(req,resp);
    }
}
