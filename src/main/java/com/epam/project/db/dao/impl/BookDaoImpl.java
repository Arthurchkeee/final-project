package com.epam.project.db.dao.impl;

import com.epam.project.db.ConnectionPool;
import com.epam.project.db.dao.BookDao;
import com.epam.project.entityes.Book;
import com.epam.project.entityes.Genre;
import com.epam.project.entityes.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {
    private static final String SELECT_BOOK_BY_ID="SELECT * FROM book WHERE id=?";
    private static final String SELECT_ALL_BOOK="SELECT * FROM book";
    @Override
    public List<Book> findAllEntities() {
        List<Book> bookList=new ArrayList<>();
        try(Connection connection=ConnectionPool.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement(SELECT_ALL_BOOK);) {

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Book findEntityById(Integer id) {
        Book book=null;
        try(Connection connection=ConnectionPool.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(SELECT_BOOK_BY_ID)) {
            preparedStatement.setInt(1,id);
            ResultSet rs= preparedStatement.executeQuery();
            while (rs.next()){
                String name=rs.getString("name");
                String genre=rs.getString("genre");
                String status=rs.getString("status");
                String author=rs.getString("author");
                book=new Book(id.longValue(),name,author, Genre.valueOf(genre), Status.valueOf(status));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return book;
    }

    @Override
    public boolean create(Book entity) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public Book Update(Book entity) {
        return null;
    }
}
