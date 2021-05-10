package com.epam.project.db.dao;

import com.epam.project.entityes.Book;

import java.util.List;

public interface BookDao extends BaseDao<Integer,Book> {
    @Override
    List<Book> findAllEntities();

    @Override
    Book findEntityById(Integer id);

    @Override
    boolean create(Book entity);

    @Override
    boolean delete(Integer id);

    @Override
    Book Update(Book entity);
}
