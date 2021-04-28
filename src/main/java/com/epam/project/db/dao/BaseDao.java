package com.epam.project.db.dao;

import com.epam.project.domain.BaseEntity;

import java.util.List;

public interface BaseDao <K,T extends BaseEntity>{
    List<T> findAllEntities();
    T findEntityById(K id);
    boolean create(T t);
    boolean delete(K k);
    T Update(T t);
}
