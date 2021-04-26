package com.epam.project.domain;

public class User extends AbstractBaseEntity{
    private String password;
    private Role role;

    public User(Long id, String name, String password, Role role) {
        super(id, name);
        this.password = password;
        this.role = role;
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
