package com.epam.project.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Subscription extends AbstractBaseEntity{
    private List<Book> books=new ArrayList<>();
    private LocalDate from;
    private LocalDate to;
    private User user;
    private final Long id;
    private final String name;

    public Subscription(Long id, String name, List<Book> books, LocalDate from, LocalDate to, User user, Long id1, String name1) {
        super(id, name);
        this.books = books;
        this.from = from;
        this.to = to;
        this.user = user;
        this.id = id1;
        this.name = name1;
    }

    public List<Book> getBooks() {
        return books;
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
