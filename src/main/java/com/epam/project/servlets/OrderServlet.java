package com.epam.project.servlets;

import com.epam.project.entities.Book;
import com.epam.project.entities.Status;
import com.epam.project.service.BookService;
import com.epam.project.service.SubscriptionService;
import com.epam.project.service.impl.BookServiceImpl;
import com.epam.project.service.impl.CommentServiceImpl;
import com.epam.project.service.impl.SubscriptionServiceImpl;
import org.apache.commons.lang.time.DateUtils;

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

@WebServlet(name = "order", urlPatterns = "/order")
public class OrderServlet extends HttpServlet {
    private static final int COMMENTS_NUMBER =20;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Book book = BookServiceImpl.getInstance().findBookById(Long.valueOf(id));
        req.setAttribute("id", id);
        req.setAttribute("name", book.getName());
        req.setAttribute("author", book.getAuthor());
        req.setAttribute("status", book.getStatus());
        req.setAttribute("genre", book.getGenre());
        req.setAttribute("description", book.getDescription());
        req.setAttribute("image", book.getImage());
        req.setAttribute("counts",CommentServiceImpl.getInstance().count(Long.parseLong(id))/ COMMENTS_NUMBER +1);
        String page= req.getParameter("page") ==null ? "1" :req.getParameter("page") ;
        req.setAttribute("comments", CommentServiceImpl.getInstance().selectCommentsForPagesByBook(Long.parseLong(id),COMMENTS_NUMBER,Integer.parseInt(page)));
        RequestDispatcher view = req.getRequestDispatcher("/jsp/bookProfile.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String status = req.getParameter("status");
        HttpSession session = req.getSession();
        Long user = (Long) session.getAttribute("user_id");
        if (status.equals("SUBSCRIBE")) {
            SubscriptionServiceImpl.getInstance().orderSubscription(user, Long.valueOf(id), new Date(DateUtils.addMonths(new Date(System.currentTimeMillis()), 1).getTime()));
            BookServiceImpl.getInstance().updateBookStatus(Status.ORDERED_SUBSCRIPTION, Long.valueOf(id));
        } else {
            SubscriptionServiceImpl.getInstance().orderRoom(user, Long.valueOf(id));
            BookServiceImpl.getInstance().updateBookStatus(Status.ORDERED_ROOM, Long.valueOf(id));
        }
        RequestDispatcher view = req.getRequestDispatcher("/catalog");
        view.forward(req, resp);
    }
}
