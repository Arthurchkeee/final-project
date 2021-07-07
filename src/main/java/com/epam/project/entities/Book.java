package com.epam.project.entities;

public class Book extends AbstractBaseEntity {
    private String author;
    private Genre genre;
    private Status status;
    private String description;
    private String image;

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public Book(Long id, String name, String author, Genre genre, Status status, String description, String image){
        super(id,name);
        this.author=author;
        this.genre=genre;
        this.status=status;
        this.description=description;
        this.image=image;
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
