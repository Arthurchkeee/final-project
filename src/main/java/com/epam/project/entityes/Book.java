package com.epam.project.entityes;

public class Book extends AbstractBaseEntity {
    private String author;
    private Genre genre;
    private Status status;

    public Book(Long id, String name, String author, Genre genre, Status status){
        super(id,name);
        this.author=author;
        this.genre=genre;
        this.status=status;
    }



    public Genre getGenre() {
        return genre;
    }

    public String getAuthor() {
        return author;
    }

    public Status getStatus() {
        return status;
    }
}
