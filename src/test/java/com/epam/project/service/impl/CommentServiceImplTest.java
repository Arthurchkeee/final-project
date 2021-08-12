package com.epam.project.service.impl;

import com.epam.project.entities.Comment;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class CommentServiceImplTest {

    @Test
    void thisCommentWas() {
        assertTrue(CommentServiceImpl.getInstance().commentAlreadyCreated(new Comment(1L,"administrator",8L,new Date(System.currentTimeMillis()),"1231231231231231")));
        assertFalse(CommentServiceImpl.getInstance().commentAlreadyCreated(new Comment(null,"124",9L,new Date(System.currentTimeMillis()),"124513")));
    }
}