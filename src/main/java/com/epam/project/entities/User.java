package com.epam.project.entities;

import org.apache.commons.codec.digest.DigestUtils;

public class User extends AbstractBaseEntity {
    private String password;
    private Role role;

    public User(Long id, String name, String password, Role role) {
        super(id, name);
        this.password = DigestUtils.md5Hex(password).toUpperCase();
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}
