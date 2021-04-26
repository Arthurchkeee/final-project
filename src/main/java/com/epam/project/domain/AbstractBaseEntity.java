package com.epam.project.domain;

public abstract class AbstractBaseEntity implements BaseEntity{
    Long id;
    String name;
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}
