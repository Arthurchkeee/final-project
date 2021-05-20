package com.epam.project.service;

import com.epam.project.entities.Subscription;

import java.util.List;

public interface SubscriptionService {
    List<Subscription> findAllSubscriptions();
    Subscription findSubscriptionById(Long id);
    boolean create(Subscription entity);
    boolean delete(Long id);
    Subscription update(Subscription entity);
}
