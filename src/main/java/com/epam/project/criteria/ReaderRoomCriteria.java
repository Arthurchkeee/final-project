package com.epam.project.criteria;

import com.epam.project.entityes.Book;
import com.epam.project.entityes.ReaderRoom;
import com.epam.project.entityes.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReaderRoomCriteria extends Criteria<ReaderRoom>{
    private List<Book> books=new ArrayList<>();
    private LocalDate from;
    private LocalDate to;
    private User user;

    public ReaderRoomCriteria() {
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
