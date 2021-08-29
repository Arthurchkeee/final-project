package com.epam.project.service;

import com.epam.project.entities.Comment;

import java.util.List;

public interface CommentService {

    /**
     *
     * Finds all available subscribe
     *
     * @return {@code List<Comment>} wrapped in a{@link List}
     */
    List<Comment> findAllEntities();

    /**
     * @param id is a primary key of a table 'comment'
     * @return  {@link com.epam.project.entities.Comment}
     */
    Comment findEntityById(Long id);

    /**
     * @param entity is a {@link com.epam.project.entities.Comment}
     * @return {@code true} if create was success, otherwise {@code false}
     */
    boolean create(Comment entity);

    /**
     * @param id is a primary key of a table 'comment'
     * @return {@code true} if delete was success. otherwise {@code false}
     */
    boolean delete(Long id);

    /**
     * @param entity is a {@link com.epam.project.entities.Comment}
     * @return {@link com.epam.project.entities.Comment} from table 'comment'
     */
    Comment update(Comment entity);

    /**
     * @param bookId is a foreign key of table 'book'
     * @return count of Comments from table 'comment'
     */
    Long count(Long bookId);

    /**
     * @param bookId is a foreign key of table 'book'
     * @param commentsAmount counts of comments on page
     * @param pageNumber number of page to display
     * @return {@code List<Comment>} wrapped in a{@link List}
     */
    List<Comment> selectCommentsForPagesByBook(Long bookId, Integer commentsAmount, Integer pageNumber);

    /**
     * @param comment is a {@link com.epam.project.entities.Comment}
     * @return {@code true} if comment is exist. otherwise {@code false}
     */
    boolean isCommentExist(Comment comment);
}
