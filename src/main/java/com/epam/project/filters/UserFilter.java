package com.epam.project.filters;
import javax.servlet.annotation.WebFilter;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "userFilter", urlPatterns = {"/order","/myBooks","/canceledOrder","/canceledReturning","/catalog","/comment","/LogoutUser"}, servletNames = {"order","myBooks","canceledOrder","canceledReturning","catalog","comment","logoutUser"})
public class UserFilter implements Filter{

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        HttpSession session = httpRequest.getSession();
        final Object role = session.getAttribute("role");
        if (role==null) {
            httpRequest.setAttribute("error", "403. Forbidden");
            httpResponse.sendError(403, "Forbidden");
        } else
            filterChain.doFilter(httpRequest, httpResponse);
    }
}
