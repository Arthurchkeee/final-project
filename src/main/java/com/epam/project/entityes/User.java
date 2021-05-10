package com.epam.project.entityes;

public class User extends AbstractBaseEntity{
    private String password;
    private Role role;

    public User(Long id, String name, String password, Role role) {
        super(id, name);
        this.password = password;
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}
