package com.epam.project.service.impl;

import com.epam.project.db.dao.impl.SubscriptionDaoImpl;
import com.epam.project.entities.Status;
import com.epam.project.entities.Subscription;
import com.epam.project.service.BookService;
import com.epam.project.service.SubscriptionService;

import java.sql.Date;
import java.util.List;

public class SubscriptionServiceImpl implements SubscriptionService {
    SubscriptionDaoImpl subscriptionDao=new SubscriptionDaoImpl();
    BookService bookService=new BookServiceImpl();

    @Override
    public List<Subscription> findAllSubscriptions() {
        return subscriptionDao.findAllEntities();
    }

    @Override
    public Subscription findSubscriptionById(Long id) {
        return subscriptionDao.findEntityById(id);
    }

    @Override
    public boolean create(Subscription entity) {
        return subscriptionDao.create(entity);
    }

    @Override
    public boolean delete(Long id) {
        return subscriptionDao.delete(id);
    }

    @Override
    public Subscription update(Subscription entity) {
        return subscriptionDao.update(entity);
    }

    public Subscription ConfirmRoomSubscription(Subscription subscription){
        long millis=System.currentTimeMillis();
        Date date=new Date(millis);
        subscription.setTo(date);
        subscription.setFrom(date);
        return subscriptionDao.update(subscription);
    }

    public Subscription ConfirmSubscription(Subscription subscription,Date from, Date to){
        subscription.setFrom(from);
        subscription.setTo(to);
        return subscriptionDao.update(subscription);
    }
    @Override
    public void orderSubscription(Long user_id,Long book_id,Date to){
        subscriptionDao.order(book_id,user_id,to);
        bookService.orderBook(book_id);
    }
    @Override
    public List<Subscription> findAllBookByStatus(Status status){
        return subscriptionDao.findAllBookByStatus(status);
    }
}
