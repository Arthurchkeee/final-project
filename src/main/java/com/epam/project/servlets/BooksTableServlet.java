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
    private static final int BOOKS_NUMBER =20;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("counts",BookServiceImpl.getInstance().count()/ BOOKS_NUMBER +1);
        String page= req.getParameter("page") ==null ? "1" :req.getParameter("page") ;
        req.setAttribute("books",BookServiceImpl.getInstance().selectBooksForPages(BOOKS_NUMBER,Integer.parseInt(page)));
        RequestDispatcher view=req.getRequestDispatcher("jsp/books.jsp");
        view.forward(req,resp);
    }
}
