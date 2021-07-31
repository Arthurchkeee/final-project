package com.epam.project.filters;

import com.epam.project.entities.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter(filterName = "userFilter", urlPatterns = {"/librarian","/returning"},servletNames = {"LibrarianServlet", "ReturningServlet"})
public class LibrarianFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest= (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session= httpRequest.getSession();
        final Object role = session.getAttribute("role");
        if(!Role.LIBRARIAN.equals(role)){
            httpRequest.setAttribute("error", "403. Forbidden");
            httpResponse.sendError(403, "Forbidden");
        }else
            filterChain.doFilter(httpRequest,httpResponse);
    }
}
