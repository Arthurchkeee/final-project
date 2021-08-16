package com.epam.project.servlets;

import com.epam.project.service.impl.CommentServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "deleteComment", urlPatterns = "/deleteComment")
public class DeleteCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommentServiceImpl.getInstance().delete(Long.valueOf(req.getParameter("commentId")));
        req.setAttribute("id",req.getParameter("id"));
        req.getRequestDispatcher("/order").forward(req,resp);

    }
}
