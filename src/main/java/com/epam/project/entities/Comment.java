package com.epam.project.entities;

import java.sql.Date;

public class Comment extends AbstractBaseEntity {
    private Long bookId;
    private Date date;
    private String text;


    public Long getBookId() {
        return bookId;
    }

    public Date getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public Comment(Long id, String name, Long bookId, Date date, String text) {
        super(id, name);
        this.bookId = bookId;
        this.date = date;
        this.text = text;
    }
}
