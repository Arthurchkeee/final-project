package com.epam.project.db.dao;

import com.epam.project.entities.Book;
import com.epam.project.entities.Status;

import java.sql.Date;
import java.util.List;

public interface BookDao extends BaseDao<Long,Book> {
    @Override
    List<Book> findAllEntities();

    @Override
    Book findEntityById(Long id);

    @Override
    boolean create(Book entity);

    @Override
    boolean delete(Long id);

    @Override
    Book update(Book entity);

    List<Book> findBookByAuthor(String author);

    List<Book> findBookByStatus(Status status);

    void updateBookStatus(Status status,Long id);


}
