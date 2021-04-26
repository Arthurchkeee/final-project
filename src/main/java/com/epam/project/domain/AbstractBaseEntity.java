package com.epam.project.domain;

public abstract class AbstractBaseEntity implements BaseEntity{
    private final Long id;
    private final String name;

    protected AbstractBaseEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}
