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
import java.util.List;

@WebServlet(name="canceledOrder",urlPatterns = "/canceledOrder")
public class CanceledOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session= req.getSession();
        List<Subscription> subscriptionList=  SubscriptionServiceImpl.getInstance().findAllSubscriptionByUser((Long) session.getAttribute("user_id"));
        req.setAttribute("subscriptions",subscriptionList);
        RequestDispatcher view= req.getRequestDispatcher("jsp/orders.jsp");
        view.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SubscriptionServiceImpl.getInstance().delete(Long.valueOf(req.getParameter("id")));
        BookServiceImpl.getInstance().updateBookStatus(Status.FREE,Long.valueOf(req.getParameter("book_id")));
        HttpSession session= req.getSession();
        if(req.getParameter("flag").equals("lib")&& session.getAttribute("role").equals("LIBRARIAN")){
            RequestDispatcher view= req.getRequestDispatcher("/librarian");
            view.forward(req,resp);
        }else
        doGet(req, resp);
    }
}
