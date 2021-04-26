package com.epam.project.domain;

public class Book extends AbstractBaseEntity {
    String name;
    String author;
    Long id;
    String genre;
    String status;

    public String getGenre() {
        return genre;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getStatus() {
        return status;
    }
}
