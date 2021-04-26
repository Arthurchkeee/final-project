package com.epam.project.domain;

import java.util.ArrayList;
import java.util.List;

public class Catalog extends AbstractBaseEntity{
    private final Long id;
    private final String name;
    private List<Book> books=new ArrayList<>();

    public Catalog(Long id, String name, Long id1, String name1, List<Book> books) {
        super(id, name);
        this.id = id1;
        this.name = name1;
        this.books = books;
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    public List<Book> getBooks() {
        return books;
    }
}
