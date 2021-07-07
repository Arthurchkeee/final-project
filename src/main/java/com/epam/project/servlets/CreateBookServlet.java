package com.epam.project.servlets;

import com.epam.project.entities.Book;
import com.epam.project.entities.Genre;
import com.epam.project.entities.Status;
import com.epam.project.service.BookService;
import com.epam.project.service.impl.BookServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "createBook", urlPatterns = "/createBook")
public class CreateBookServlet extends HttpServlet {
    BookService service=BookServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Book book=new Book(null,req.getParameter("name"),req.getParameter("author"), Genre.valueOf(req.getParameter("genre")), Status.FREE,req.getParameter("description"),req.getParameter("image"));
        service.create(book);
        RequestDispatcher view= req.getRequestDispatcher("jsp/createBook.jsp");
        view.forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/createBook.jsp").forward(req, resp);
    }
}
