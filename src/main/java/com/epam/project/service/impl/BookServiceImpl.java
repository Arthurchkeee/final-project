package com.epam.project.service.impl;

import com.epam.project.db.dao.BookDao;
import com.epam.project.db.dao.impl.BookDaoImpl;
import com.epam.project.entities.Book;
import com.epam.project.entities.Status;
import com.epam.project.service.BookService;

import java.util.List;

public final class BookServiceImpl implements BookService {
    private static BookServiceImpl instance;
    private  BookDao bookDao;

    public static BookServiceImpl getInstance(){
        if(instance ==null){
            instance =new BookServiceImpl();
        }
        return instance;
    }

    private BookServiceImpl(){
        bookDao=new BookDaoImpl();
    }

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
    public List<Book> findBooksByAuthor(String author) {
        return bookDao.findBooksByAuthor(author);
    }

    @Override
    public List<Book> findAllFreeBooks() {
        return bookDao.findBooksByStatus(Status.FREE);
    }


    @Override
    public void updateBookStatus(Status status,Long id){
        bookDao.updateBookStatus(status,id);
    }

    @Override
    public Long count(){
        return bookDao.count();
    }

    public List<Book> selectSomeBooks(Integer number, Integer page){
        return bookDao.selectSomeBooks(number,page);
    }
}
