package com.epam.project.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface CommandRequest {

    Object getAttribute(String name);

    String getRequestParameter(String param);

    HttpSession getSession(boolean flag);

    void setAttribute(String name, Object value);


}
