package com.epam.project.entityes;

public abstract class AbstractBaseEntity implements BaseEntity{
    private Long id;
    private String name;

    protected AbstractBaseEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}