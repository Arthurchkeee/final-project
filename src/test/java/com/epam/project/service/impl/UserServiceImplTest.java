package com.epam.project.service.impl;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    UserServiceImpl userService = new UserServiceImpl();

    @org.junit.jupiter.api.Test
    void getAccess() {
        assertTrue(userService.getAccess("Alexei", "12345"));
        assertFalse(userService.getAccess("Artur", "12345"));
        assertFalse(userService.getAccess("Oleg", "1234"));
    }
}