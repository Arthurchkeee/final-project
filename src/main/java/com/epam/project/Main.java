package com.epam.project;


import com.epam.project.db.dao.SubscriptionDao;
import com.epam.project.db.dao.impl.BookDaoImpl;
import com.epam.project.db.dao.impl.UserDaoImpl;
import com.epam.project.entities.*;
import com.epam.project.service.BookService;
import com.epam.project.service.SubscriptionService;
import com.epam.project.service.impl.BookServiceImpl;
import com.epam.project.service.impl.SubscriptionServiceImpl;
import com.epam.project.service.impl.UserServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.List;


public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        LOGGER.info("test");

    }
}
