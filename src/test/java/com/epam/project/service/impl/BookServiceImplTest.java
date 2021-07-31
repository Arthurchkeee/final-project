package com.epam.project.service.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceImplTest {

    @Test
    void count() {
        assertEquals(BookServiceImpl.getInstance().count(),22L);
    }
}