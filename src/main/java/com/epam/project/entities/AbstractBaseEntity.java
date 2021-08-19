package com.epam.project.entities;

public abstract class AbstractBaseEntity implements BaseEntity {
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

    @Override
    public String toString() {
        return "AbstractBaseEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
