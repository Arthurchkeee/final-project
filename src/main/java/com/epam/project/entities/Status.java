package com.epam.project.entities;

public enum Status implements BaseEntity{
    SUBSCRIPTION(1L),
    ROOM(2L),
    FREE(3L),
    REQUEST_ROOM(4L),
    REQUEST_SUBSCRIPTION(5L)
    ;
    private final Long id;

    Status(Long id) {
        this.id=id;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return this.name();
    }
}
