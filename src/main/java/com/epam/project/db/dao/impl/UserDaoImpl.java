package com.epam.project.db.dao.impl;

import com.epam.project.db.ConnectionPool;
import com.epam.project.db.ConnectionProxy;
import com.epam.project.db.dao.UserDao;
import com.epam.project.entities.Role;
import com.epam.project.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static UserDaoImpl instance;
    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);
    private static final String SELECT_ALL_USER = "SELECT * FROM user";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE id=?";
    private static final String CREATE_BOOK = "INSERT INTO user(name,password,role) VALUES(?,?,?)";
    private static final String DELETE_BOOK_BY_ID = "DELETE FROM user WHERE id=?";
    private static final String UPDATE_USER_BY_ID = "UPDATE user SET name=?,password=?,role=? WHERE id=?;";
    private static final String SELECT_USER_BY_NAME = "SELECT * FROM user WHERE name=?";
    private static final String COUNT = "SELECT COUNT(*) AS row_count FROM user";
    private static final String SELECT_USERS_FOR_PAGE = "SELECT* FROM user ORDER BY id LIMIT ?,?";

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        if (instance == null)
            instance = new UserDaoImpl();
        return instance;
    }

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
            LOGGER.error("Failed to find all users, " + throwables);
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
            LOGGER.error("Failed to find user by id, " + throwables);
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
            LOGGER.error("Failed to create user, " + throwables);
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
            LOGGER.error("Failed to delete user, " + throwables);
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
            LOGGER.error("Failed to update user, " + throwables);
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
            LOGGER.error("Failed to get password, " + throwables);
        }
        return password;
    }

    public User findUserByLogin(String login) {
        User user = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_NAME)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                user = new User(id, name, password, Role.valueOf(role));
            }
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);
        } catch (SQLException throwables) {
            LOGGER.error("Failed to find user by login, " + throwables);
        }
        return user;
    }

    public Integer count() {
        Integer count = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(COUNT);
            resultSet.next();
            count = resultSet.getInt("row_count");
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);
        } catch (SQLException throwables) {
            LOGGER.error("Failed to find the count of rows" + throwables);
        }
        return count;
    }

    public List<User> selectUsersForPages(Integer booksAmount, Integer pageNumber) {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS_FOR_PAGE)) {
            preparedStatement.setInt(2, booksAmount);
            preparedStatement.setInt(1, booksAmount * (pageNumber - 1));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                users.add(new User(id, name, password, Role.valueOf(role)));
            }
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);
        } catch (SQLException throwables) {
            LOGGER.error("Failed to select " + booksAmount + " users " + throwables);
        }
        return users;
    }
}
