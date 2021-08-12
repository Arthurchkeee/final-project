package com.epam.project.db.dao;

import com.epam.project.db.dao.BaseDao;
import com.epam.project.entities.Comment;

import java.util.List;

public interface CommentDao extends BaseDao<Long, Comment> {
    @Override
    List<Comment> findAllEntities();

    @Override
    Comment findEntityById(Long id);

    @Override
    boolean create(Comment entity);

    @Override
    boolean delete(Long id);

    @Override
    Comment update(Comment entity);

    List<Comment> findCommentsByBook(Long id);

    boolean commentAlreadyCreated(Comment comment);
}
