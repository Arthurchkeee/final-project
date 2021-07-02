package com.epam.project.service;

import com.epam.project.entities.Status;
import com.epam.project.entities.Subscription;

import java.sql.Date;
import java.util.List;

public interface SubscriptionService {
    List<Subscription> findAllSubscriptions();
    Subscription findSubscriptionById(Long id);
    boolean create(Subscription entity);
    boolean delete(Long id);
    Subscription update(Subscription entity);
    public void orderSubscription(Long user_id, Long book_id, Date to);
    public void orderRoom(Long user_id,Long book_id);
    public List<Subscription> findAllBookByStatus(Status status);
    public List<Subscription> findAllSubscriptionByUser(Long user_id);
    public void deleteSubscription(Long id,Long book_id);
}
