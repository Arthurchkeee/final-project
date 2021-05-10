package com.epam.project.db.dao;

import com.epam.project.entityes.BaseEntity;

import java.util.List;

public interface BaseDao <K,E extends BaseEntity>{
    List<E> findAllEntities();
    E findEntityById(K id);
    boolean create(E entity);
    boolean delete(K id);
    E Update(E entity);
}