package com.epam.project.command;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name="controller",urlPatterns = "/controller")
public class MainServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String command = req.getParameter("command");
        final Command of = Command.of(command);
        final CommandResponse execute = of.execute(new CommandRequest() {
            @Override
            public Object getAttribute(String name) {
                return req.getAttribute(name);
            }

            @Override
            public String getRequestParameter(String param) {
                return req.getParameter(param);
            }

            @Override
            public HttpSession getSession(boolean flag) {
                return req.getSession(false);
            }

            @Override
            public void setAttribute(String name, Object value) {
                req.setAttribute(name, value);
            }
        });

        if (execute.isRedirect()) {
            resp.sendRedirect(execute.getDestination().getPath());
        } else {
            req.getRequestDispatcher(execute.getDestination().getPath()).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String commandName = req.getParameter("command");
        final Command command = Command.of(commandName);
        final CommandResponse response = command.execute(new CommandRequest() {
            @Override
            public Object getAttribute(String name) {
                return req.getAttribute(name);
            }

            @Override
            public String getRequestParameter(String param) {
                return req.getParameter(param);
            }

            @Override
            public HttpSession getSession(boolean flag) {
                return req.getSession(flag);
            }

            @Override
            public void setAttribute(String name, Object value) {
                req.setAttribute(name, value);
            }
        });
        if (response.isRedirect()) {
            resp.sendRedirect(response.getDestination().getPath());
        } else {
            req.getRequestDispatcher(response.getDestination().getPath()).forward(req, resp);
        }
    }
}
