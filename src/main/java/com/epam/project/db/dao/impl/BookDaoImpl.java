package com.epam.project.db.dao.impl;

import com.epam.project.db.ConnectionPool;
import com.epam.project.db.ConnectionProxy;
import com.epam.project.db.dao.BookDao;
import com.epam.project.entities.Book;
import com.epam.project.entities.Genre;
import com.epam.project.entities.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookDaoImpl implements BookDao {

    private static final Logger LOGGER = LogManager.getLogger(BookDaoImpl.class);

    private static final String SELECT_BOOK_BY_ID = "SELECT * FROM book WHERE id=?";
    private static final String SELECT_ALL_BOOK = "SELECT * FROM book";
    private static final String CREATE_BOOK_BY = "INSERT INTO book (name,author,genre,status,description,image) VALUES (?,?,?,?,?,?);";
    private static final String DELETE_BOOK_BY_ID = "DELETE  FROM book WHERE id=?";
    private static final String UPDATE_BOOK_BY_ID = "UPDATE book SET name=?,author=?,genre=?,status=?,description=?, image=? WHERE id=?;";
    private static final String SELECT_BOOK_BY_AUTHOR="SELECT * FROM book WHERE author=?";
    private static final String SELECT_BOOK_BY_STATUS="SELECT * FROM book WHERE status=?";
    private static final String UPDATE_BOOK_STATUS="UPDATE book SET status=? WHERE id=?";
    private static final String SELECT_SOME_BOOK ="SELECT* FROM book ORDER BY id LIMIT ?,?";
    private static final String COUNT="SELECT COUNT(*) AS row_count FROM book";

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
                String description=resultSet.getString("description");
                String image=resultSet.getString("image");
                bookList.add(new Book(id, name, author, Genre.valueOf(genre), Status.valueOf(status),description,image));
            }
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);
        } catch (SQLException throwables) {
            LOGGER.error("Failed to find all books,"+throwables);
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
                String description=rs.getString("description");
                String image= rs.getString("image");
                book = new Book(id, name, author, Genre.valueOf(genre), Status.valueOf(status),description,image);
            }
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);

        } catch (SQLException throwables) {
            LOGGER.error("Failed to find book by id, "+throwables);
        }
        return book;
    }

    @Override
    public boolean create(Book entity) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_BOOK_BY)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getAuthor());
            preparedStatement.setString(3, entity.getGenre().getName());
            preparedStatement.setString(4, entity.getStatus().getName());
            preparedStatement.setString(5, entity.getDescription());
            preparedStatement.setString(6, entity.getImage());
            preparedStatement.executeUpdate();
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);
            return true;
        } catch (SQLException throwables) {
            LOGGER.error("Failed to create new book, "+throwables);
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
            LOGGER.error("Failed to delete book, "+throwables);
        }
        return false;
    }

    @Override
    public Book update(Book entity) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOK_BY_ID)) {
            preparedStatement.setLong(7, entity.getId());
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getAuthor());
            preparedStatement.setString(3, entity.getGenre().getName());
            preparedStatement.setString(4, entity.getStatus().getName());
            preparedStatement.setString(5,entity.getDescription());
            preparedStatement.setString(6, entity.getImage());
            preparedStatement.executeUpdate();
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);

        } catch (SQLException throwables) {
            LOGGER.error("Failed to update book, "+throwables);
        }
        return entity;
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        List<Book> bookList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOK_BY_AUTHOR);) {
            preparedStatement.setString(1,author);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String genre = resultSet.getString("genre");
                String status = resultSet.getString("status");
                String description=resultSet.getString("description");
                String image=resultSet.getString("image");
                bookList.add(new Book(id, name, author, Genre.valueOf(genre), Status.valueOf(status),description,image));
            }
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);
        } catch (SQLException throwables) {
            LOGGER.error("Failed to find books by author, "+throwables);
        }
        return bookList;
    }

    public List<Book> findBooksByStatus(Status status){
        List<Book> bookList=new ArrayList<>();
        try(Connection connection=ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement= connection.prepareStatement(SELECT_BOOK_BY_STATUS)){
            preparedStatement.setString(1,status.getName());
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String genre = resultSet.getString("genre");
                String author = resultSet.getString("author");
                String description=resultSet.getString("description");
                String image=resultSet.getString("image");
                bookList.add(new Book(id, name, author, Genre.valueOf(genre), status,description,image));
            }
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);
        } catch (SQLException throwables) {
            LOGGER.error("Failed to find books by status, "+throwables);
        }
        return bookList;
    }

    @Override
    public void updateBookStatus(Status status,Long id){
        try(Connection connection=ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement(UPDATE_BOOK_STATUS)){
            preparedStatement.setString(1,status.getName());
            preparedStatement.setLong(2,id);
            preparedStatement.executeUpdate();
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);
        } catch (SQLException throwables) {
            LOGGER.error("Failed to update book status, "+throwables);
        }

    }
    @Override
    public Long count(){
        Long count = null;
        try(Connection connection=ConnectionPool.getInstance().getConnection();
            Statement statement=connection.createStatement()){
            ResultSet resultSet= statement.executeQuery(COUNT);
            resultSet.next();
            count = resultSet.getLong("row_count");
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    public List<Book> selectSomeBooks(Integer number,Integer page){
        List<Book> books=new ArrayList<>();
        try(Connection connection=ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement(SELECT_SOME_BOOK)){
            preparedStatement.setInt(2,number);
            preparedStatement.setInt(1,number*(page-1));
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String genre = resultSet.getString("genre");
                String status = resultSet.getString("status");
                String author = resultSet.getString("author");
                String description=resultSet.getString("description");
                String image=resultSet.getString("image");
                books.add(new Book(id, name, author, Genre.valueOf(genre), Status.valueOf(status),description,image));
            }
            ConnectionPool.getInstance().returnConnection((ConnectionProxy) connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return books;
    }
}
