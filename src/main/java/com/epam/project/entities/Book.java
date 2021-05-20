package com.epam.project.entities;

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

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return super.toString()+ "Book{" +
                "author='" + author + '\'' +
                ", genre=" + genre +
                ", status=" + status +
                '}';
    }
}
