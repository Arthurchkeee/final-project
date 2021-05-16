package com.epam.project;

import com.epam.project.db.ConnectionPool;
import com.epam.project.db.dao.impl.BookDaoImpl;
import com.epam.project.entityes.Book;
import com.epam.project.entityes.Status;

public class Main {
    public static void main(String[] args) {
        BookDaoImpl bookDao=new BookDaoImpl();
        Book book=bookDao.findEntityById(1);
        System.out.println(book);
    }
}
