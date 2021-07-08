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
import java.util.List;


public class Main {
    public static void main(String[] args) {
        if(SubscriptionServiceImpl.getInstance().findAllSubscriptionByUser(34L).size()==0)
        System.out.println("true");
        else
            System.out.println("false");

    }
}
