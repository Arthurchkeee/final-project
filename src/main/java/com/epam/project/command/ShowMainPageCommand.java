package com.epam.project.command;

import com.epam.project.service.BookService;
import com.epam.project.service.impl.BookServiceImpl;


import static com.epam.project.command.ServletDestination.MAIN_PAGE;


public class ShowMainPageCommand implements Command {

    BookService bookService = new BookServiceImpl();

    @Override
    public CommandResponse execute(CommandRequest request) {


        // todo
        return () -> MAIN_PAGE;
    }
}
