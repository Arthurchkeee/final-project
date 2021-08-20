package com.epam.project.db.dao.impl;

import com.epam.project.db.ConnectionPool;
import com.epam.project.db.ConnectionProxy;
import com.epam.project.entities.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SubscriptionDaoImpl implements com.epam.project.db.dao.SubscriptionDao {

    private static SubscriptionDaoImpl instance;
    private static final Logger LOGGER = LogManager.getLogger(SubscriptionDaoImpl.class);

    private static final String SELECT_ALL_SUBSCRIPTION = "SELECT * FROM subscription INNER JOIN user u on subscription.user_id = u.id INNER JOIN book b on subscription.book_id = b.id";
    private static final String SELECT_SUBSCRIPTION_BY_ID = "SELECT * FROM subscription INNER JOIN user u on subscription.user_id = u.id INNER JOIN book b on subscription.book_id = b.id WHERE subscription.id=?";
    private static final String CREATE_SUBSCRIPTION = "INSERT INTO subscription(id,book_id,day_from,day_to,user_id) VALUES(?,?,?,?,?)";
    private static final String DELETE_SUBSCRIPTION_BY_ID = "DELETE FROM subscription WHERE id=?";
    private static final String UPDATE_SUBSCRIPTION = "UPDATE subscription SET book_id=?,day_from=?,day_to=?,user_id=? WHERE id=?";
    private static final String ORDER_SUBSCRIPTION = "INSERT INTO subscription(book_id,day_from,day_to,user_id) VALUES(?,?,?,?)";
    private static final String SELECT_SUBSCRIPTION_BY_STATUS = "SELECT * FROM subscription INNER JOIN user u on subscription.user_id = u.id INNER JOIN book b on subscription.book_id = b.id WHERE b.status=?";
    private static final String SELECT_SUBSCRIPTION_BY_USER = "SELECT * FROM subscription INNER JOIN user u on subscription.user_id = u.id INNER JOIN book b on subscription.book_id = b.id WHERE u.id=? ORDER by subscription.day_to";
    private static final String RENEW_BOOK = "UPDATE subscription SET  day_to=? WHERE id=?";
    private static final String SELECT_SUBSCRIPTIONS_BY_LIST_OF_STATUSES = "SELECT * FROM subscription INNER JOIN user u on subscription.user_id = u.id INNER JOIN book b on subscription.book_id = b.id  WHERE b.status IN (%s) ORDER BY subscription.id LIMIT ?,?";
    private static final String SELECT_SUBSCRIPTIONS_BY_LIST_OF_STATUSES_AND_USER = "SELECT * FROM subscription INNER JOIN user u on subscription.user_id = u.id INNER JOIN book b on subscription.book_id = b.id WHERE u.id=? AND b.status IN (%s) ORDER BY subscription.id LIMIT ?,?";
    private static final String COUNT = "SELECT COUNT(*) AS row_count FROM subscription INNER JOIN book b on subscription.book_id = b.id WHERE b.status IN (%s)";
    private static final String COUNT_FOR_USER = "SELECT COUNT(*) AS row_count FROM subscription INNER JOIN user u on subscription.user_id = u.id INNER JOIN book b on subscription.book_id = b.id WHERE u.id=? AND b.status IN (%s)";


    private SubscriptionDaoImpl() {
    }

    public static SubscriptionDaoImpl getInstance() {
        if (instance == null)
            instance = new SubscriptionDaoImpl();
        return instance;
    }

    @Override
    public List<Subscription> findAllEntities() {
        List<Subscription> subscriptionList = new ArrayList<Subscription>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SUBSCRIPTION)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("subscription.id");
                Long bookId = resultSet.getLong("b.id");
                String bookName = resultSet.getString("b.name");
                String bookGenre = resultSet.getString("b.genre");
                String bookStatus = resultSet.getString("b.status");
                String bookAuthor = resultSet.getString("b.author");
                String bookDescription = resultSet.getString("b.description");
                String bookImage = resultSet.getString("b.image");
                Date from = resultSet.getDate("day_from");
                Date to = resultSet.getDate("day_to");
                Long userId = resultSet.getLong("u.id");
                String userName = resultSet.getString("u.name");
                String userPassword = resultSet.getString("u.password");
                String userRole = resultSet.getString("u.role");
                subscriptionList.add(new Subscription(id, userName, new Book(bookId, bookName, bookAuthor, Genre.valueOf(bookGenre), Status.valueOf(bookStatus), bookDescription, bookImage), from, to, new User(userId, userName, userPassword, Role.valueOf(userRole))));
            }
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);
        } catch (SQLException throwables) {
            LOGGER.error("Failed to find all subscriptions, " + throwables);
        }
        return subscriptionList;
    }

    @Override
    public Subscription findEntityById(Long id) {
        Subscription subscription = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SUBSCRIPTION_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long subsId = resultSet.getLong("subscription.id");
                Long bookId = resultSet.getLong("b.id");
                String bookName = resultSet.getString("b.name");
                String bookGenre = resultSet.getString("b.genre");
                String bookStatus = resultSet.getString("b.status");
                String bookAuthor = resultSet.getString("b.author");
                String bookDescription = resultSet.getString("b.description");
                String bookImage = resultSet.getString("b.image");
                Date from = resultSet.getDate("day_from");
                Date to = resultSet.getDate("day_to");
                Long userId = resultSet.getLong("u.id");
                String userName = resultSet.getString("u.name");
                String userPassword = resultSet.getString("u.password");
                String userRole = resultSet.getString("u.role");
                subscription = new Subscription(subsId, userName, new Book(bookId, bookName, bookAuthor, Genre.valueOf(bookGenre), Status.valueOf(bookStatus), bookDescription, bookImage), from, to, new User(userId, userName, userPassword, Role.valueOf(userRole)));
            }
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);
        } catch (SQLException throwables) {
            LOGGER.error("Failed to find subscription by id, " + throwables);
        }
        return subscription;
    }

    @Override
    public boolean create(Subscription entity) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_SUBSCRIPTION)) {
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.setLong(2, entity.getBooks().getId());
            preparedStatement.setDate(3, entity.getFrom());
            preparedStatement.setDate(4, entity.getTo());
            preparedStatement.setLong(5, entity.getUser().getId());
            preparedStatement.executeUpdate();
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);
            return true;
        } catch (SQLException throwables) {
            LOGGER.error("Failed to create subscription, " + throwables);
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SUBSCRIPTION_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);
            return true;
        } catch (SQLException throwables) {
            LOGGER.error("Failed to delete subscription, " + throwables);
        }
        return false;
    }

    @Override
    public Subscription update(Subscription entity) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SUBSCRIPTION)) {
            preparedStatement.setLong(5, entity.getId());
            preparedStatement.setLong(1, entity.getBooks().getId());
            preparedStatement.setDate(2, entity.getFrom());
            preparedStatement.setDate(3, entity.getTo());
            preparedStatement.setLong(4, entity.getUser().getId());
            preparedStatement.executeUpdate();
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);

        } catch (SQLException throwables) {
            LOGGER.error("Failed to update subscription, " + throwables);
        }
        return entity;
    }

    @Override
    public boolean order(Long bookId, Long userId, Date to) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ORDER_SUBSCRIPTION)) {
            preparedStatement.setLong(1, bookId);
            long millis = System.currentTimeMillis();
            preparedStatement.setDate(2, new Date(millis));
            preparedStatement.setDate(3, to);
            preparedStatement.setLong(4, userId);
            preparedStatement.executeUpdate();
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);
            return true;
        } catch (SQLException throwables) {
            LOGGER.error("Failed to order book, " + throwables);
        }
        return false;
    }

    @Override
    public List<Subscription> findAllBooksByStatus(Status status) {
        List<Subscription> subscriptionList = new ArrayList<Subscription>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SUBSCRIPTION_BY_STATUS)) {
            preparedStatement.setString(1, status.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("subscription.id");
                Long bookId = resultSet.getLong("b.id");
                String bookName = resultSet.getString("b.name");
                String bookGenre = resultSet.getString("b.genre");
                String bookStatus = resultSet.getString("b.status");
                String bookAuthor = resultSet.getString("b.author");
                String bookDescription = resultSet.getString("b.description");
                String bookImage = resultSet.getString("b.image");
                Date from = resultSet.getDate("day_from");
                Date to = resultSet.getDate("day_to");
                Long userId = resultSet.getLong("u.id");
                String userName = resultSet.getString("u.name");
                String userPassword = resultSet.getString("u.password");
                String userRole = resultSet.getString("u.role");
                subscriptionList.add(new Subscription(id, userName, new Book(bookId, bookName, bookAuthor, Genre.valueOf(bookGenre), Status.valueOf(bookStatus), bookDescription, bookImage), from, to, new User(userId, userName, userPassword, Role.valueOf(userRole))));
            }
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);
        } catch (SQLException throwables) {
            LOGGER.error("Failed to find all books by status, " + throwables);
        }
        return subscriptionList;
    }

    @Override
    public List<Subscription> findAllSubscriptionsByUser(Long userId) {
        List<Subscription> subscriptionList = new ArrayList<Subscription>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SUBSCRIPTION_BY_USER)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("subscription.id");
                Long bookId = resultSet.getLong("b.id");
                String bookName = resultSet.getString("b.name");
                String bookGenre = resultSet.getString("b.genre");
                String bookStatus = resultSet.getString("b.status");
                String bookAuthor = resultSet.getString("b.author");
                String bookDescription = resultSet.getString("b.description");
                String bookImage = resultSet.getString("b.image");
                Date from = resultSet.getDate("day_from");
                Date to = resultSet.getDate("day_to");
                String userName = resultSet.getString("u.name");
                String userPassword = resultSet.getString("u.password");
                String userRole = resultSet.getString("u.role");
                subscriptionList.add(new Subscription(id, userName, new Book(bookId, bookName, bookAuthor, Genre.valueOf(bookGenre), Status.valueOf(bookStatus), bookDescription, bookImage), from, to, new User(userId, userName, userPassword, Role.valueOf(userRole))));
            }
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);
        } catch (SQLException throwables) {
            LOGGER.error("Failed to find all subscriptions by user, " + throwables);
        }
        return subscriptionList;
    }

    public boolean updateDateTo(Long id, Date to) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RENEW_BOOK)) {
            preparedStatement.setLong(2, id);
            preparedStatement.setDate(1, to);
            preparedStatement.executeUpdate();
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public List<Subscription> findSubscriptionsByBookStatuses(List<Status> statuses, Integer booksAmount, Integer pageNumber) {
        List<Subscription> subscriptionList = new ArrayList<Subscription>();
        String inSql = String.join(",", Collections.nCopies(statuses.size(), "?"));
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     String.format(SELECT_SUBSCRIPTIONS_BY_LIST_OF_STATUSES, inSql))) {
            for (int i = 0; i < statuses.size(); i++) {
                preparedStatement.setString(i + 1, statuses.get(i).getName());
            }
            preparedStatement.setInt(statuses.size() + 2, booksAmount);
            preparedStatement.setInt(statuses.size()+1, booksAmount * (pageNumber - 1));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("subscription.id");
                Long bookId = resultSet.getLong("b.id");
                String bookName = resultSet.getString("b.name");
                String bookGenre = resultSet.getString("b.genre");
                String bookStatus = resultSet.getString("b.status");
                String bookAuthor = resultSet.getString("b.author");
                String bookDescription = resultSet.getString("b.description");
                String bookImage = resultSet.getString("b.image");
                Date from = resultSet.getDate("day_from");
                Date to = resultSet.getDate("day_to");
                Long userId = resultSet.getLong("u.id");
                String userName = resultSet.getString("u.name");
                String userPassword = resultSet.getString("u.password");
                String userRole = resultSet.getString("u.role");
                subscriptionList.add(new Subscription(id, userName, new Book(bookId, bookName, bookAuthor, Genre.valueOf(bookGenre), Status.valueOf(bookStatus), bookDescription, bookImage), from, to, new User(userId, userName, userPassword, Role.valueOf(userRole))));
            }
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);
        } catch (SQLException throwables) {
            LOGGER.error("Failed to find all subscriptions, " + throwables);
        }
        return subscriptionList;
    }


    public List<Subscription> findSubscriptionsByBookStatusesAndUser(List<Status> statuses, Long userId, Integer booksAmount, Integer pageNumber) {
        List<Subscription> subscriptionList = new ArrayList<Subscription>();
        String inSql = String.join(",", Collections.nCopies(statuses.size(), "?"));
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(String.format(SELECT_SUBSCRIPTIONS_BY_LIST_OF_STATUSES_AND_USER, inSql))) {
            preparedStatement.setLong(1, userId);
            for (int i = 0; i < statuses.size(); i++) {
                preparedStatement.setString(i + 2, statuses.get(i).getName());
            }
            preparedStatement.setInt(statuses.size() + 3, booksAmount);
            preparedStatement.setInt(statuses.size()+2, booksAmount * (pageNumber - 1));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("subscription.id");
                Long bookId = resultSet.getLong("b.id");
                String bookName = resultSet.getString("b.name");
                String bookGenre = resultSet.getString("b.genre");
                String bookStatus = resultSet.getString("b.status");
                String bookAuthor = resultSet.getString("b.author");
                String bookDescription = resultSet.getString("b.description");
                String bookImage = resultSet.getString("b.image");
                Date from = resultSet.getDate("day_from");
                Date to = resultSet.getDate("day_to");
                String userName = resultSet.getString("u.name");
                String userPassword = resultSet.getString("u.password");
                String userRole = resultSet.getString("u.role");
                subscriptionList.add(new Subscription(id, userName, new Book(bookId, bookName, bookAuthor, Genre.valueOf(bookGenre), Status.valueOf(bookStatus), bookDescription, bookImage), from, to, new User(userId, userName, userPassword, Role.valueOf(userRole))));
            }
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);
        } catch (SQLException throwables) {
            LOGGER.error("Failed to find all subscriptions, " + throwables);
        }
        return subscriptionList;
    }

    public Long count(List<Status> statuses) {
        Long count = null;
        String inSql = String.join(",", Collections.nCopies(statuses.size(), "?"));
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(String.format(COUNT, inSql))) {
            for (int i = 0; i < statuses.size(); i++) {
                preparedStatement.setString(i + 1, statuses.get(i).getName());
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            count = resultSet.getLong("row_count");
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    public Long countForUser(List<Status> statuses,Long userId){
        Long count=null;
        String inSql = String.join(",", Collections.nCopies(statuses.size(), "?"));
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(String.format(COUNT_FOR_USER, inSql))) {
            preparedStatement.setLong(1, userId);
            for (int i = 0; i < statuses.size(); i++) {
                preparedStatement.setString(i + 2, statuses.get(i).getName());
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            count = resultSet.getLong("row_count");
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
}