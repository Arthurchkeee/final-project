package com.epam.project.domain;

import java.util.ArrayList;
import java.util.List;

public class Catalog extends AbstractBaseEntity{
    Long id;
    String name;
    List<Book> books=new ArrayList<>();

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
        return books;
    }
}
