package com.epam.project.service.impl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    UserServiceImpl userService = UserServiceImpl.getInstance();

    @Test
    void getAccess() {
        assertTrue(userService.getAccess("Alexei", "12345"));
        assertTrue(userService.getAccess("Artur", "12345"));
        assertFalse(userService.getAccess("Andrew", "1234"));
    }
}