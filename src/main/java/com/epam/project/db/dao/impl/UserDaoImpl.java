package com.epam.project.db.dao.impl;

import com.epam.project.db.dao.UserDao;
import com.epam.project.entityes.User;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public List<User> findAllEntities() {
        return null;
    }

    @Override
    public User findEntityById(Integer id) {
        return null;
    }

    @Override
    public boolean create(User entity) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public User Update(User entity) {
        return null;
    }
}
