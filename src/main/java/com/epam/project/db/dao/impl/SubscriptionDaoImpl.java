package com.epam.project.db.dao.impl;

import com.epam.project.db.ConnectionPool;
import com.epam.project.db.dao.BaseDao;
import com.epam.project.entities.AbstractBaseEntity;
import com.epam.project.entities.Subscription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SubscriptionDaoImpl implements BaseDao<Long,Subscription> {
    private static final String SELECT_ALL_SUBSCRIPTION="SELECT*FROM subscribe WHERE id=?";
    @Override
    public List<Subscription> findAllEntities() {
        List<Subscription> subscriptionList =new ArrayList<Subscription>();
        try (Connection connection= ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement=connection.prepareStatement(SELECT_ALL_SUBSCRIPTION)) {
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                Long id=resultSet.getLong("id");
                Long book_id=resultSet.getLong("book_id");
                Date from =resultSet.getDate("from");
                Date to=resultSet.getDate("to");
                Long user_id=resultSet.getLong("user_id");
                //subscriptionList.add(new Subscription(id,))

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Subscription findEntityById(Long id) {
        return null;
    }

    @Override
    public boolean create(Subscription entity) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Subscription update(Subscription entity) {
        return null;
    }
}
