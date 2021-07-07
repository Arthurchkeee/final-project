package com.epam.project.servlets;

import com.epam.project.service.impl.BookServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="deleteBook", urlPatterns = "/deleteBook")
public class DeleteBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");
        BookServiceImpl.getInstance().delete(Long.valueOf(id));
        RequestDispatcher view= req.getRequestDispatcher("/books");
        view.forward(req,resp);
    }
}
