package com.epam.project.db.dao.impl;

import com.epam.project.db.ConnectionPool;
import com.epam.project.db.dao.BaseDao;
import com.epam.project.entities.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class SubscriptionDaoImpl implements BaseDao<Long,Subscription> {
    private static final String SELECT_ALL_SUBSCRIPTION="SELECT * FROM subscribe INNER JOIN user u on subscribe.user_id = u.id INNER JOIN book b on subscribe.book_id = b.id";
    private static final String SELECT_SUBSCRIPTION_BY_ID="SELECT * FROM subscribe INNER JOIN user u on subscribe.user_id = u.id INNER JOIN book b on subscribe.book_id = b.id WHERE subscribe.id=?";
    private static final String CREATE_SUBSCRIPTION="INSERT INTO subscribe(id,book_id,day_from,day_to,user_id) VALUES(?,?,?,?,?)";
    private static final String DELETE_SUBSCRIPTION_BY_ID="DELETE FROM subscribe WHERE id=?";
    private static final String UPDATE_SUBSCRIPTION="UPDATE subscribe SET book_id=?,day_from=?,day_to=?,user_id=? WHERE id=?;";
    @Override
    public List<Subscription> findAllEntities() {
        List<Subscription> subscriptionList =new ArrayList<Subscription>();
        try (Connection connection= ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement=connection.prepareStatement(SELECT_ALL_SUBSCRIPTION)) {
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                Long id=resultSet.getLong("subscribe.id");
                Long book_id=resultSet.getLong("b.id");
                String book_name=resultSet.getString("b.name");
                String book_genre = resultSet.getString("b.genre");
                String book_status = resultSet.getString("b.status");
                String book_author = resultSet.getString("b.author");
                Date from =resultSet.getDate("day_from");
                Date to=resultSet.getDate("day_to");
                Long user_id=resultSet.getLong("u.id");
                String user_name = resultSet.getString("u.name");
                String user_password = resultSet.getString("u.password");
                String user_role = resultSet.getString("u.role");
                subscriptionList.add(new Subscription(id,user_name,new Book(book_id,book_name,book_author, Genre.valueOf(book_genre), Status.valueOf(book_status)),from,to,new User(user_id,user_name,user_password,Role.valueOf(user_role))));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return subscriptionList;
    }

    @Override
    public Subscription findEntityById(Long id) {
        Subscription subscription=null;
        try(Connection connection=ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement(SELECT_SUBSCRIPTION_BY_ID)) {
            preparedStatement.setLong(1,id);
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long subs_id = resultSet.getLong("subscribe.id");
                Long book_id = resultSet.getLong("b.id");
                String book_name = resultSet.getString("b.name");
                String book_genre = resultSet.getString("b.genre");
                String book_status = resultSet.getString("b.status");
                String book_author = resultSet.getString("b.author");
                Date from = resultSet.getDate("day_from");
                Date to = resultSet.getDate("day_to");
                Long user_id = resultSet.getLong("u.id");
                String user_name = resultSet.getString("u.name");
                String user_password = resultSet.getString("u.password");
                String user_role = resultSet.getString("u.role");
                subscription = new Subscription(subs_id, user_name, new Book(book_id, book_name, book_author, Genre.valueOf(book_genre), Status.valueOf(book_status)), from, to, new User(user_id, user_name, user_password, Role.valueOf(user_role)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return subscription;
    }

    @Override
    public boolean create(Subscription entity) {
        try(Connection connection=ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement= connection.prepareStatement(CREATE_SUBSCRIPTION)) {
            preparedStatement.setLong(1,entity.getId());
            preparedStatement.setLong(2,entity.getBooks().getId());
            preparedStatement.setDate(3, entity.getFrom());
            preparedStatement.setDate(4, entity.getTo());
            preparedStatement.setLong(5,entity.getUser().getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        try(Connection connection=ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement= connection.prepareStatement(DELETE_SUBSCRIPTION_BY_ID)) {
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public Subscription update(Subscription entity) {
        try(Connection connection=ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement= connection.prepareStatement(UPDATE_SUBSCRIPTION)) {
            preparedStatement.setLong(5,entity.getId());
            preparedStatement.setLong(1,entity.getBooks().getId());
            preparedStatement.setDate(2,entity.getFrom());
            preparedStatement.setDate(3,entity.getTo());
            preparedStatement.setLong(4,entity.getUser().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
