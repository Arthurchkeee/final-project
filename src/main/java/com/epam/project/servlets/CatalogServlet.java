package com.epam.project.servlets;

import com.epam.project.service.BookService;
import com.epam.project.service.impl.BookServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name="catalog",urlPatterns = "/catalog")
public class CatalogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setAttribute("books",BookServiceImpl.getInstance().findAllBooks());
        RequestDispatcher view= req.getRequestDispatcher("jsp/catalog.jsp");
        view.forward(req,resp);
    }
}
