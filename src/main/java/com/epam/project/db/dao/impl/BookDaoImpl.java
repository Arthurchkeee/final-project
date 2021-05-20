package com.epam.project.db.dao.impl;

import com.epam.project.db.ConnectionPool;
import com.epam.project.db.dao.BaseDao;
import com.epam.project.db.dao.BookDao;
import com.epam.project.entities.Book;
import com.epam.project.entities.Genre;
import com.epam.project.entities.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {
    private static final String SELECT_BOOK_BY_ID = "SELECT * FROM book WHERE id=?";
    private static final String SELECT_ALL_BOOK = "SELECT * FROM book";
    private static final String CREATE_BOOK_BY = "INSERT INTO book (id,name,author,genre,status) VALUES (?,?,?,?,?);";
    private static final String DELETE_BOOK_BY_ID = "DELETE  FROM book WHERE id=?";
    private static final String UPDATE_BOOK_BY_ID = "UPDATE book SET name=?,author=?,genre=?,status=? WHERE id=?;";
    private static final String SELECT_BOOK_BY_AUTHOR="SELECT * FROM book WHERE author=?";

    @Override
    public List<Book> findAllEntities() {
        List<Book> bookList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOK);) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String genre = resultSet.getString("genre");
                String status = resultSet.getString("status");
                String author = resultSet.getString("author");
                bookList.add(new Book(id, name, author, Genre.valueOf(genre), Status.valueOf(status)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bookList;
    }

    @Override
    public  Book findEntityById(Long id) {
        Book book = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOK_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String genre = rs.getString("genre");
                String status = rs.getString("status");
                String author = rs.getString("author");
                book = new Book(id, name, author, Genre.valueOf(genre), Status.valueOf(status));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return book;
    }

    @Override
    public boolean create(Book entity) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_BOOK_BY)) {
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setString(3, entity.getAuthor());
            preparedStatement.setString(4, entity.getGenre().getName());
            preparedStatement.setString(5, entity.getStatus().getName());
            preparedStatement.executeUpdate();
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
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public Book update(Book entity) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOK_BY_ID)) {
            preparedStatement.setLong(5, entity.getId());
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getAuthor());
            preparedStatement.setString(3, entity.getGenre().getName());
            preparedStatement.setString(4, entity.getStatus().getName());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return entity;
    }

    @Override
    public List<Book> findBookByAuthor(String author) {
        List<Book> bookList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOK_BY_AUTHOR);) {
            preparedStatement.setString(1,"author");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String genre = resultSet.getString("genre");
                String status = resultSet.getString("status");
                bookList.add(new Book(id, name, author, Genre.valueOf(genre), Status.valueOf(status)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bookList;
    }
}
