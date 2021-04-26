package com.epam.project.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReaderRoom extends AbstractBaseEntity{
    private Long id;
    private List<Book> books=new ArrayList<>();
    private LocalDate from;
    private LocalDate to;
    private User user;

    public ReaderRoom(Long id, String name, Long id1, List<Book> books, LocalDate from, LocalDate to, User user) {
        super(id, name);
        this.id = id1;
        this.books = books;
        this.from = from;
        this.to = to;
        this.user = user;
    }



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
