package com.epam.project.command;

import com.epam.project.entities.User;
import com.epam.project.service.UserService;
import com.epam.project.service.impl.UserServiceImpl;


import javax.servlet.http.HttpSession;

import java.util.Optional;

import static com.epam.project.command.ServletDestination.INDEX;


public class UserLoginCommand implements Command {

    private UserService userService = new UserServiceImpl();

    public static final CommandResponse COMMAND_RESPONSE = new CommandResponse() {
        @Override
        public boolean isRedirect() {
            return true;
        }

        @Override
        public Destination getDestination() {
            return INDEX;
        }
    };

    @Override
    public CommandResponse execute(CommandRequest request) {
        final String login = request.getRequestParameter("login");
        System.out.println(login);
        final String pass = request.getRequestParameter("password");
        System.out.println(pass);


        if (userService.getAccess(login.toString(), pass.toString())) {
            User user = userService.findUserByLogin(login);

            final HttpSession session = request.getSession(true);
            session.setAttribute("username", user.getName());
            session.setAttribute("role", user.getRole());

            session.setMaxInactiveInterval(100);
            return COMMAND_RESPONSE;
        }

        return null;
    }
}
