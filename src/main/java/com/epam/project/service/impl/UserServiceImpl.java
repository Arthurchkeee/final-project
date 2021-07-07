package com.epam.project.service.impl;

import com.epam.project.db.dao.UserDao;
import com.epam.project.db.dao.impl.UserDaoImpl;
import com.epam.project.entities.User;
import com.epam.project.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;

public final class UserServiceImpl implements UserService {
    private static UserServiceImpl INSTANCE;

    private UserDao userDao;

    private UserServiceImpl(){
        userDao = new UserDaoImpl();
    }

    public static UserServiceImpl getInstance(){
        if(INSTANCE==null){
            INSTANCE=new UserServiceImpl();
        }
        return INSTANCE;
    }

    @Override
    public List<User> findAllUsers() {
        return userDao.findAllEntities();
    }

    @Override
    public User findUserById(Long id) {
        return userDao.findEntityById(id);
    }

    @Override
    public boolean create(User entity) {
        return userDao.create(entity);
    }

    @Override
    public boolean delete(Long id) {
        return userDao.delete(id);
    }

    @Override
    public User update(User entity) {
        return userDao.update(entity);
    }

    @Override
    public boolean getAccess(String login, String password) {
        String md5Hex = DigestUtils
                .md5Hex(password).toUpperCase();
        return md5Hex.equals(userDao.getPassword(login));
    }
    @Override
    public User findUserByLogin(String login){
        return userDao.findUserByLogin(login);
    }
}
