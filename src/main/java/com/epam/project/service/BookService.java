package com.epam.project.service;

import com.epam.project.entities.Book;

import java.util.List;

public interface BookService {
    List<Book> findAllBooks();

    Book findBookById(Long id);

    boolean create(Book entity);

    boolean delete(Long id);

    Book update(Book entity);

    Book returnBook(Book book);

    Book roomRequestBook(Book book);

    Book subscribeRequestBook(Book book);

    Book getBookInRoom(Book book);

    Book getBookInSubscribe(Book book);

    List<Book> findBookByAuthor(String author);
}
