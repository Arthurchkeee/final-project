package com.epam.project.db.dao.impl;

import com.epam.project.db.dao.SubscriptionDao;
import com.epam.project.entityes.Subscription;

import java.util.List;

public class SubscriptionDaoImpl implements SubscriptionDao {

    @Override
    public List<Subscription> findAllEntities() {
        return null;
    }

    @Override
    public Subscription findEntityById(Integer id) {
        return null;
    }

    @Override
    public boolean create(Subscription entity) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public Subscription Update(Subscription entity) {
        return null;
    }
}