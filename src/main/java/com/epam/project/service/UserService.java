package com.epam.project.service;

import com.epam.project.entities.User;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();

    User findUserById(Long id);

    boolean create(User entity);

    boolean delete(Long id);

    User update(User entity);

    boolean getAccess(String login, String password);

    User findUserByLogin(String login);
}
