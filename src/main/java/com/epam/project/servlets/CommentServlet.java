package com.epam.project.servlets;

import com.epam.project.db.dao.impl.CommentDaoImpl;
import com.epam.project.entities.Comment;
import com.epam.project.service.impl.CommentServiceImpl;
import com.epam.project.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "comment",urlPatterns = "/comment")
public class CommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session=req.getSession();
        Comment comment=new Comment(null,session.getAttribute("username").toString(),Long.parseLong(req.getParameter("id")),new Date(System.currentTimeMillis()),req.getParameter("comment"));
        if(!CommentServiceImpl.getInstance().thisCommentWas(comment)){
        CommentServiceImpl.getInstance().create(comment);
        }
        req.setAttribute("id",Long.parseLong(req.getParameter("id")));
        req.getRequestDispatcher("/order").forward(req,resp);
    }
}
