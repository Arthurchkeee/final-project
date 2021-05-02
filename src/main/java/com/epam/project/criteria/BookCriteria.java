package com.epam.project.criteria;

import com.epam.project.entityes.Book;
import com.epam.project.entityes.Genre;
import com.epam.project.entityes.Status;

public class BookCriteria extends Criteria<Book>{
    private String author;
    private Genre genre=null;
    private Status status=null;

    public BookCriteria(){}

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
