package com.epam.project.criteria;

import com.epam.project.entities.BaseEntity;

public abstract class Criteria <T extends BaseEntity> {
    private Long id;
    private String name;

    public Criteria(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
