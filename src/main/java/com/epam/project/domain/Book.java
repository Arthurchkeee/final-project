package com.epam.project.domain;

public class Book extends AbstractBaseEntity {
    private String author;
    private Genre genre;
    private Status status;

    Book(Long id, String name, String author,Genre genre, Status status ){
        super(id,name);
        this.author=author;
        this.genre=genre;
        this.status=status;
    }



    public Genre getGenre() {
        return genre;
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    public String getAuthor() {
        return author;
    }

    public Status getStatus() {
        return status;
    }
}
