package com.epam.project.servlets;

import com.epam.project.service.impl.BookServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="books",urlPatterns = "/books")
public class BooksTableServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("counts",BookServiceImpl.getInstance().count()/20+1);
        String page=req.getParameter("page");
        if(page==null)
            page="1";
        req.setAttribute("books",BookServiceImpl.getInstance().get20Books((Integer.parseInt(page)-1)*20));
        RequestDispatcher view=req.getRequestDispatcher("jsp/books.jsp");
        view.forward(req,resp);
    }
}
