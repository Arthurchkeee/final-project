package com.epam.project.db.dao;

import com.epam.project.entityes.User;

import java.util.List;

public interface UserDao extends BaseDao<Integer,User>{
    @Override
    List<User> findAllEntities();

    @Override
    User findEntityById(Integer id);

    @Override
    boolean create(User entity);

    @Override
    boolean delete(Integer id);

    @Override
    User Update(User entity);
}
