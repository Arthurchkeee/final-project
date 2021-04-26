package com.epam.project.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Subscription extends AbstractBaseEntity{
    Long id;
    List<Book> books=new ArrayList<>();
    LocalDate from;
    LocalDate to;
    User user;

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }
}
