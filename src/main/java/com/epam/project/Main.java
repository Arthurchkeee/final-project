package com.epam.project;


import com.epam.project.db.dao.SubscriptionDao;
import com.epam.project.db.dao.impl.UserDaoImpl;
import com.epam.project.entities.*;
import com.epam.project.service.BookService;
import com.epam.project.service.SubscriptionService;
import com.epam.project.service.impl.BookServiceImpl;
import com.epam.project.service.impl.SubscriptionServiceImpl;
import com.epam.project.service.impl.UserServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Date;


public class Main {
    public static void main(String[] args) {
        BookService bookService=new BookServiceImpl();
        for (int i = 0; i < 12; i++) {
            bookService.findAllBooks();
        }

    }
}
