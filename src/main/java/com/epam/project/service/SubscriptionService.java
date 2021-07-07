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
    void orderSubscription(Long userId, Long bookId, Date to);
    void orderRoom(Long userId,Long bookId);
    List<Subscription> findAllBooksByStatus(Status status);
    List<Subscription> findAllSubscriptionByUser(Long userId);
    void deleteSubscription(Long id,Long bookId);
}
