package com.epam.project.entityes;

import java.util.ArrayList;
import java.util.List;

public class Catalog extends AbstractBaseEntity{
    private List<Book> books=new ArrayList<>();

    public Catalog(Long id, String name,  List<Book> books) {
        super(id, name);
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }
}
