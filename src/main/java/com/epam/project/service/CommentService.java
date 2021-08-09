package com.epam.project.service;

import com.epam.project.entities.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findAllEntities();

    Comment findEntityById(Long id);

    boolean create(Comment entity);

    boolean delete(Long id);

    Comment update(Comment entity);

    List<Comment> findCommentsByBook(Long id);

    boolean thisCommentWas(Comment comment);
}
