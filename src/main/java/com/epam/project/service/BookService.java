package com.epam.project.service;

import com.epam.project.entities.Book;
import com.epam.project.entities.Status;

import java.util.List;

public interface BookService {

    /**
     * Finds all available books
     *
     * @return {@code List<Book>} wrapped in a{@link List}
     */
    List<Book> findAllBooks();

    /**
     * @param id is a primary key of a table 'book'
     * @return the {@link com.epam.project.entities.Book} with {@link com.epam.project.entities.Genre}
     */
    Book findBookById(Long id);


    /**
     * @param entity is a {@link com.epam.project.entities.Book}
     * @return {@code true} if create was success, otherwise {@code false}
     */
    boolean create(Book entity);

    /**
     * @param id is a primary key of a table 'book'
     * @return {@code true} if delete was success. otherwise {@code false}
     */
    boolean delete(Long id);


    /**
     * @param entity is a {@link com.epam.project.entities.Book}
     * @return {@link com.epam.project.entities.Book} from table 'book'
     */
    Book update(Book entity);

    /**
     * @param status is a {@link com.epam.project.entities.Status}
     * @param id is a primary key from table 'book'
     */
    void updateBookStatus(Status status, Long id);


    /**
     * @return count of Books from table 'book'
     */
    Long count();

    /**
     * @param booksAmount counts of books on page
     * @param pageNumber number of page to display
     * @return {@code List<Book>} wrapped in a{@link List}
     */
    List<Book> selectBooksForPages(Integer booksAmount, Integer pageNumber);
}
