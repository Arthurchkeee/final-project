package com.epam.project.db.dao.impl;

import com.epam.project.db.ConnectionPool;
import com.epam.project.db.ConnectionProxy;
import com.epam.project.db.dao.BaseDao;
import com.epam.project.db.dao.UserDao;
import com.epam.project.entities.Role;
import com.epam.project.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final String SELECT_ALL_USER = "SELECT * FROM user";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE id=?";
    private static final String CREATE_BOOK = "INSERT INTO user(name,password,role) VALUES(?,?,?)";
    private static final String DELETE_BOOK_BY_ID = "DELETE FROM user WHERE id=?";
    private static final String UPDATE_USER_BY_ID = "UPDATE user SET name=?,password=?,role=? WHERE id=?;";
    private static final String SELECT_USER_BY_NAME = "SELECT * FROM user WHERE name=?";

    @Override
    public List<User> findAllEntities() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USER)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                userList.add(new User(id, name, password, Role.valueOf(role)));
            }
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    @Override
    public User findEntityById(Long id) {
        User user = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                user = new User(id, name, password, Role.valueOf(role));
            }
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean create(User entity) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_BOOK)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getRole().getName());
            preparedStatement.executeUpdate();
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOK_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public User update(User entity) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_BY_ID)) {
            preparedStatement.setLong(4, entity.getId());
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getRole().getName());
            preparedStatement.executeUpdate();
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return entity;
    }

    @Override
    public String getPassword(String login) {
        String password = "";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_NAME)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                password = resultSet.getString("password");
            }
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return password;
    }

    public User findUserByLogin(String login){
        User user=null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_NAME)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id= resultSet.getLong("id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                user = new User(id, name, password, Role.valueOf(role));
            }
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(user);
        return user;
    }
}
