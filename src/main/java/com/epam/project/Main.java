package com.epam.project;

import com.epam.project.entityes.Book;
import com.epam.project.entityes.Status;

public class Main {
    public static void main(String[] args) {
        Book book=new Book(6L,"gay","cheb",null, Status.FREE);
        System.out.println(book.getId());
    }
}
