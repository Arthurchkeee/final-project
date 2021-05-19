package com.epam.project;


import com.epam.project.db.dao.impl.BookDaoImpl;
import com.epam.project.entityes.Book;
import com.epam.project.entityes.Genre;
import com.epam.project.entityes.Status;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        BookDaoImpl bookDao=new BookDaoImpl();
        bookDao.create(new Book(25L,"Ten Little Niggers","Agata Kristy", Genre.DRAMA,Status.FREE));
        bookDao.delete(25);
        bookDao.create(new Book(25L,"Ten Little Niggers","Agata Kristy", Genre.DRAMA,Status.FREE));
        bookDao.update(new Book(25L,"Ten Little Niggers","Agata Kristy", Genre.DRAMA,Status.ROOM));
        List<Book> books=bookDao.findAllEntities();
        System.out.println("a");
    }
}
