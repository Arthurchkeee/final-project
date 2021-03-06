package com.epam.project.service.impl;

import com.epam.project.db.dao.CommentDao;
import com.epam.project.db.dao.impl.CommentDaoImpl;
import com.epam.project.entities.Comment;
import com.epam.project.service.CommentService;

import java.util.List;

public class CommentServiceImpl implements CommentService {
    private static CommentServiceImpl instance;
    private final CommentDao commentDao;

    public static CommentServiceImpl getInstance() {
        if (instance == null) {
            instance = new CommentServiceImpl();
        }
        return instance;
    }

    private CommentServiceImpl() {
        commentDao = CommentDaoImpl.getInstance();
    }

    @Override
    public List<Comment> findAllEntities() {
        return commentDao.findAllEntities();
    }

    @Override
    public Comment findEntityById(Long id) {
        return commentDao.findEntityById(id);
    }

    @Override
    public boolean create(Comment entity) {
        return commentDao.create(entity);
    }

    @Override
    public boolean delete(Long id) {
        return commentDao.delete(id);
    }

    @Override
    public Comment update(Comment entity) {
        return commentDao.update(entity);
    }

    @Override
    public Long count(Long bookId) {
        return commentDao.count(bookId);
    }

    @Override
    public List<Comment> selectCommentsForPagesByBook(Long bookId, Integer commentsAmount, Integer pageNumber) {
        return commentDao.selectCommentsForPagesByBook(bookId,commentsAmount,pageNumber);
    }

    public boolean isCommentExist(Comment comment) {
        return commentDao.isCommentExist(comment);
    }
}
