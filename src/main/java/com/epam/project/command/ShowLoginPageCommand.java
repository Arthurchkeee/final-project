package com.epam.project.command;

import static com.epam.project.command.ServletDestination.LOGIN_PAGE;

public class ShowLoginPageCommand implements Command {

    @Override
    public CommandResponse execute(CommandRequest request) {
        return () -> LOGIN_PAGE;
    }
}