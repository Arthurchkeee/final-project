package com.epam.project.service.impl;

import com.epam.project.db.dao.SubscriptionDao;
import com.epam.project.db.dao.impl.SubscriptionDaoImpl;
import com.epam.project.entities.Status;
import com.epam.project.entities.Subscription;
import com.epam.project.service.SubscriptionService;

import java.sql.Date;
import java.util.List;

public final class SubscriptionServiceImpl implements SubscriptionService {
    private static SubscriptionServiceImpl instance;
    private static SubscriptionDao subscriptionDao;

    private SubscriptionServiceImpl() {
        subscriptionDao =new SubscriptionDaoImpl();
    }

    public static SubscriptionServiceImpl getInstance(){
        if(instance ==null){
            instance =new SubscriptionServiceImpl();
        }
        return instance;
    }

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
        Date date=new Date(System.currentTimeMillis());
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
    public void orderSubscription(Long userId, Long bookId, Date to){
        subscriptionDao.order(bookId, userId,to);
    }
    @Override
    public void orderRoom(Long userId, Long bookId){
        subscriptionDao.order(bookId, userId,new Date(System.currentTimeMillis()));
    }
    @Override

    public List<Subscription> findAllBooksByStatus(Status status){
        return subscriptionDao.findAllBooksByStatus(status);
    }

    @Override
    public List<Subscription> findAllSubscriptionsByUser(Long userId){
        return subscriptionDao.findAllSubscriptionsByUser(userId);
    }
    @Override
    public void deleteSubscription(Long id,Long bookId){
        subscriptionDao.delete(id);
    }

    public boolean renewSubscription(Long id, Date to){
        return subscriptionDao.renewSubscription(id,to);
    }
}
