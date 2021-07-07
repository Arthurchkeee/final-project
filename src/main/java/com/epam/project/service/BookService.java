package com.epam.project.service;

import com.epam.project.entities.Book;
import com.epam.project.entities.Status;

import java.util.List;

public interface BookService {
    List<Book> findAllBooks();

    Book findBookById(Long id);

    boolean create(Book entity);

    boolean delete(Long id);

    Book update(Book entity);

    List<Book> findBooksByAuthor(String author);

    List<Book> findAllFreeBooks();

    void updateBookStatus(Status status, Long id);
}
