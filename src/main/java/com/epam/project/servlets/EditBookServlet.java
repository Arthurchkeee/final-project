package com.epam.project.servlets;

import com.epam.project.entities.Book;
import com.epam.project.entities.Genre;
import com.epam.project.entities.Status;
import com.epam.project.service.impl.BookServiceImpl;
import com.epam.project.validator.BookValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "editBook", urlPatterns = "/editBook")
public class EditBookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("id",req.getParameter("id"));
        req.setAttribute("name",req.getParameter("name"));
        req.setAttribute("author",req.getParameter("author"));
        resp.sendRedirect(req.getContextPath()+"/editBook");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(BookValidator.getInstance().isValid(req.getParameter("book_name"),req.getParameter("author_name"), req.getParameter("description"),req.getParameter("image"))) {
            BookServiceImpl.getInstance().update(new Book(Long.valueOf(req.getParameter("id")), req.getParameter("book_name"), req.getParameter("author_name"), Genre.valueOf(req.getParameter("genre")), Status.FREE, req.getParameter("description"), req.getParameter("image")));
        }
        RequestDispatcher view=req.getRequestDispatcher("/books");
        view.forward(req,resp);
    }
}
