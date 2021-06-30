package com.epam.project.service.impl;

import com.epam.project.db.dao.BookDao;
import com.epam.project.db.dao.impl.BookDaoImpl;
import com.epam.project.entities.Book;
import com.epam.project.entities.Status;
import com.epam.project.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();

    @Override
    public List<Book> findAllBooks() {
        return bookDao.findAllEntities();
    }

    @Override
    public Book findBookById(Long id) {
        return bookDao.findEntityById(id);
    }

    @Override
    public boolean create(Book entity) {
        return bookDao.create(entity);
    }

    @Override
    public boolean delete(Long id) {
        return bookDao.delete(id);
    }

    @Override
    public Book update(Book entity) {
        return bookDao.update(entity);
    }



    @Override
    public Book returnBook(Book book) {
        book.setStatus(Status.FREE);
        return update(book);
    }

    @Override
    public Book roomRequestBook(Book book) {
        book.setStatus(Status.ORDERED_ROOM);
        return update(book);
    }

    @Override
    public Book subscribeRequestBook(Book book) {
        book.setStatus(Status.ORDERED_SUBSCRIPTION);
        return update(book);
    }

    @Override
    public Book getBookInRoom(Book book) {
        book.setStatus(Status.ROOM);
        return update(book);
    }

    @Override
    public Book getBookInSubscribe(Book book) {
        book.setStatus(Status.SUBSCRIPTION);
        return update(book);
    }

    @Override
    public List<Book> findBookByAuthor(String author) {
        return bookDao.findBookByAuthor(author);
    }

    @Override
    public List<Book> findAllFreeBook() {
        return bookDao.findBookByStatus(Status.FREE);
    }

    @Override
    public void orderBook(Long id){
        bookDao.updateBookStatus(Status.ORDERED_SUBSCRIPTION,id);
    }

    @Override
    public void updateBookStatus(Status status,Long id){
        bookDao.updateBookStatus(status,id);
    }
}
