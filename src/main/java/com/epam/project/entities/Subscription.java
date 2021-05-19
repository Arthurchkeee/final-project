package com.epam.project.entities;

import java.util.Date;

public class Subscription extends AbstractBaseEntity{
    private Book books;
    private Date from;
    private Date to;
    private User user;

    public Subscription(Long id, String name, Book books, Date from, Date to, User user) {
        super(id, name);
        this.books = books;
        this.from = from;
        this.to = to;
        this.user = user;
    }

    public Book getBooks() {
        return books;
    }



    public User getUser() {
        return user;
    }

    public Date getFrom() {
        return from;
    }

    public Date getTo() {
        return to;
    }
}
