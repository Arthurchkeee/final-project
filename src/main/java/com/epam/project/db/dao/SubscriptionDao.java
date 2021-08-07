package com.epam.project.db.dao;

import com.epam.project.entities.Status;
import com.epam.project.entities.Subscription;

import java.sql.Date;
import java.util.List;

public interface SubscriptionDao extends BaseDao<Long, Subscription>{
    @Override
    List<Subscription> findAllEntities();

    @Override
    Subscription findEntityById(Long id);

    @Override
    boolean create(Subscription entity);

    @Override
    boolean delete(Long id);

    @Override
    Subscription update(Subscription entity);

    boolean order(Long bookId, Long userId, Date to);

    List<Subscription> findAllBooksByStatus(Status status);

    List<Subscription> findAllSubscriptionsByUser(Long userId);

    boolean renewSubscription(Long id,Date to);
}
