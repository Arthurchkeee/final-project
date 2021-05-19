package com.epam.project.criteria;

import com.epam.project.entities.Role;
import com.epam.project.entities.User;

public class UserCriteria extends Criteria<User>{
    private String password;
    private Role role;

    public UserCriteria(){}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
