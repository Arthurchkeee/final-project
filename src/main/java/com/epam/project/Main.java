package com.epam.project;


import com.epam.project.db.dao.impl.BookDaoImpl;
import com.epam.project.db.dao.impl.SubscriptionDaoImpl;
import com.epam.project.db.dao.impl.UserDaoImpl;
import com.epam.project.entities.*;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        BookDaoImpl bookDao=new BookDaoImpl();
        UserDaoImpl userDao=new UserDaoImpl();
        SubscriptionDaoImpl subscriptionDao=new SubscriptionDaoImpl();
        System.out.println(subscriptionDao.findEntityById(3L));
        //subscriptionDao.create(new Subscription(6L,"Gen",bookDao.findEntityById(25L), Date.valueOf("2021-05-20"),Date.valueOf("2021-05-20"),userDao.findEntityById(5L)));

        subscriptionDao.update(new Subscription(6L,"Gen",bookDao.findEntityById(25L), Date.valueOf("2021-01-20"),Date.valueOf("2021-07-20"),userDao.findEntityById(5L)));
        System.out.println();
    }
}
