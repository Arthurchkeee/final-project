package com.epam.project.db.dao;

import com.epam.project.entities.User;

import java.util.List;

public interface UserDao extends BaseDao<Long,User>{
    @Override
    List<User> findAllEntities();

    @Override
    User findEntityById(Long id);

    @Override
    boolean create(User entity);

    @Override
    boolean delete(Long id);

    @Override
    User update(User entity);

    String getPassword(String login);

    User findUserByLogin(String login);
}
