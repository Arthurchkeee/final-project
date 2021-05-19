package com.epam.project.db.dao;

import com.epam.project.entityes.Subscription;

import java.util.List;

public interface SubscriptionDao extends BaseDao<Integer, Subscription>{
    @Override
    List<Subscription> findAllEntities();

    @Override
    Subscription findEntityById(Integer id);

    @Override
    boolean create(Subscription entity);

    @Override
    boolean delete(Integer id);

    @Override
    Subscription update(Subscription entity);
}
