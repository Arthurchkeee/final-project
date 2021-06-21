package com.epam.project.filter;

import com.epam.project.entities.Role;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@WebFilter(urlPatterns = "/controller/*")
public class AuthFilter implements Filter {


    String[] whiteList = {"show_login", "login"};


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        final HttpSession session = ((HttpServletRequest) request).getSession();

        final Object role = session.getAttribute("role");
        final String command = request.getParameter("command");
        if (Arrays.stream(whiteList).noneMatch(command::equalsIgnoreCase) && (role == null || role.toString().equals(Role.ANONYMOUS.toString()))) {
            ((HttpServletResponse) response).sendRedirect("/WEB-INF/jsp/login.jsp");
        } else {
            chain.doFilter(request, response);
        }

        System.out.println("AAAAAA");
        System.out.println(role);
        System.out.println("/AAAAAA");
    }
}
