package com.epam.project.db.dao.impl;

import com.epam.project.db.ConnectionPool;
import com.epam.project.db.ConnectionProxy;
import com.epam.project.db.dao.CommentDao;
import com.epam.project.entities.Comment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl implements CommentDao {
    private static CommentDaoImpl instance;
    private static final Logger LOGGER = LogManager.getLogger(CommentDaoImpl.class);
    public static final String SELECT_ALL_COMMENTS = "SELECT* FROM comment";
    private static final String CREATE_COMMENT = "INSERT INTO comment (username,date,text,book) VALUES (?,?,?,?);";
    private static final String DELETE_COMMENT_BY_ID = "DELETE  FROM comment WHERE id=?";
    private static final String UPDATE_COMMENT_BY_ID = "UPDATE comment SET username=?,date=?,text=?,book=? WHERE id=?";
    private static final String SELECT_COMMENT_BY_ID = "SELECT FROM comment WHERE id=?";
    private static final String SELECT_COMMENT = "SELECT* FROM comment WHERE username=? AND date=? AND text=? AND book=?";
    private static final String COUNT = "SELECT COUNT(*) AS row_count FROM comment WHERE book=?";
    private static final String SELECT_COMMENTS_FOR_PAGE_BY_BOOK = "SELECT* FROM comment WHERE book=? ORDER BY id LIMIT ?,?";


    private CommentDaoImpl() {
    }

    public static CommentDaoImpl getInstance() {
        if (instance == null) {
            instance = new CommentDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Comment> findAllEntities() {
        List<Comment> comments = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COMMENTS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String username = resultSet.getString("username");
                Date date = resultSet.getDate("date");
                String text = resultSet.getString("text");
                Long bookId = resultSet.getLong("book");
                comments.add(new Comment(id, username, bookId, date, text));
            }
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);
        } catch (SQLException throwables) {
            LOGGER.error("Failed to find all users " + throwables);
        }
        return comments;
    }

    @Override
    public Comment findEntityById(Long id) {
        Comment comment = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COMMENT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                comment = new Comment(id, resultSet.getString("username"), resultSet.getLong("book"), resultSet.getDate("date"), resultSet.getString("text"));
            }
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);
        } catch (SQLException throwables) {
            LOGGER.error("Failed to find comment by id, " + throwables);
        }
        return comment;
    }

    @Override
    public boolean create(Comment entity) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_COMMENT)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setDate(2, entity.getDate());
            preparedStatement.setString(3, entity.getText());
            preparedStatement.setLong(4, entity.getBookId());
            preparedStatement.executeUpdate();
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);
            return true;
        } catch (SQLException throwables) {
            LOGGER.error("Failed to create comment, " + throwables);
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COMMENT_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);
            return true;
        } catch (SQLException throwables) {
            LOGGER.error("Failed to delete comment, " + throwables);
        }
        return false;
    }

    @Override
    public Comment update(Comment entity) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COMMENT_BY_ID)) {
            preparedStatement.setLong(5, entity.getId());
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setDate(2, entity.getDate());
            preparedStatement.setString(3, entity.getText());
            preparedStatement.setLong(4, entity.getBookId());
            preparedStatement.executeUpdate();
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);

        } catch (SQLException throwables) {
            LOGGER.error("Failed to update comment, " + throwables);
        }
        return entity;
    }

    public Long count(Long bookId) {
        Long count = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(COUNT)) {
            statement.setLong(1,bookId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            count = resultSet.getLong("row_count");
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);
        } catch (SQLException throwables) {
            LOGGER.error("Failed to count comments for book "+throwables);
        }
        return count;
    }

    public List<Comment> selectCommentsForPagesByBook(Long bookId, Integer commentsAmount, Integer pageNumber) {
        List<Comment> comments = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COMMENTS_FOR_PAGE_BY_BOOK)) {
            preparedStatement.setLong(1,bookId);
            preparedStatement.setInt(3, commentsAmount);
            preparedStatement.setInt(2, commentsAmount * (pageNumber - 1));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String username = resultSet.getString("username");
                Date date = resultSet.getDate("date");
                String text = resultSet.getString("text");
                comments.add(new Comment(id, username, bookId, date, text));
            }
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);
        } catch (SQLException throwables) {
            LOGGER.error("Failed to select comments "+throwables);
        }
        return comments;
    }

    public boolean isCommentExist(Comment comment) {
        Comment comments = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COMMENT)) {
            preparedStatement.setString(1, comment.getName());
            preparedStatement.setDate(2, comment.getDate());
            preparedStatement.setString(3, comment.getText());
            preparedStatement.setLong(4, comment.getBookId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                comments = new Comment(resultSet.getLong("id"), resultSet.getString("username"), resultSet.getLong("book"), resultSet.getDate("date"), resultSet.getString("text"));
            }
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);
        } catch (SQLException throwables) {
            LOGGER.error("Failed to find comment, " + throwables);
        }
        return comments != null;
    }
}
