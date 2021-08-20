package com.epam.project.servlets;

import com.epam.project.entities.Status;
import com.epam.project.service.impl.BookServiceImpl;
import com.epam.project.service.impl.SubscriptionServiceImpl;
import org.apache.commons.lang.time.DateUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;

@WebServlet(name = "librarian", urlPatterns = "/librarian")
public class LibrarianServlet extends HttpServlet {
    private static final int SUBS_PER_PAGE =20;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("renew".equals(req.getParameter("action"))) {
            SubscriptionServiceImpl.getInstance().updateDateTo(Long.parseLong(req.getParameter("id")), new Date(DateUtils.addMonths(new Date(System.currentTimeMillis()), 1).getTime()));
            BookServiceImpl.getInstance().updateBookStatus(Status.SUBSCRIPTION, Long.valueOf(req.getParameter("book_id")));
        } else if ("delete".equals(req.getParameter("action"))) {
            SubscriptionServiceImpl.getInstance().delete(Long.valueOf(req.getParameter("id")));
            BookServiceImpl.getInstance().updateBookStatus(Status.FREE, Long.valueOf(req.getParameter("book_id")));
        } else {
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
        req.setAttribute("counts",SubscriptionServiceImpl.getInstance().count(Arrays.asList(Status.ORDERED_ROOM, Status.ORDERED_SUBSCRIPTION, Status.RENEW))/ SUBS_PER_PAGE +1);
        String page= req.getParameter("page") ==null ? "1" :req.getParameter("page") ;
        req.setAttribute("subscriptions",SubscriptionServiceImpl.getInstance().findSubscriptionsByBookStatuses(Arrays.asList(Status.ORDERED_ROOM, Status.ORDERED_SUBSCRIPTION, Status.RENEW),SUBS_PER_PAGE,Integer.parseInt(page)));
        RequestDispatcher view = req.getRequestDispatcher("jsp/librarian.jsp");
        view.forward(req, resp);
    }
}
