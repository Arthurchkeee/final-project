package com.epam.project;


import com.epam.project.db.dao.impl.UserDaoImpl;
import com.epam.project.entities.*;
import com.epam.project.service.impl.UserServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;


public class Main {
    public static void main(String[] args) {
        UserDaoImpl userDao = new UserDaoImpl();
        UserServiceImpl userService = new UserServiceImpl();
        String md5Hex = DigestUtils
                .md5Hex("12345").toUpperCase();
        //userDao.create(new User(25L,"Alexei",md5Hex,Role.ADMIN));
        System.out.println("Arthur" + userService.getAccess("Artur", "1234"));
        System.out.println("Oleg" + userService.getAccess("Oleg", "1234"));
        System.out.println("Alexei" + userService.getAccess("Alexei", "12345"));
    }
}
