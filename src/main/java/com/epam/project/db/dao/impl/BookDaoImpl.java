package com.epam.project.db.dao.impl;

import com.epam.project.db.dao.BookDao;
import com.epam.project.entityes.Book;

import java.util.List;

public class BookDaoImpl implements BookDao {
    @Override
    public List<Book> findAllEntities() {
        return null;
    }

    @Override
    public Book findEntityById(Integer id) {
        return null;
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
