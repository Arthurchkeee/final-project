package com.epam.project.entities;

public enum Status implements BaseEntity {
    SUBSCRIPTION(1L),
    ROOM(2L),
    FREE(3L),
    ORDERED_SUBSCRIPTION(4L),
    ORDERED_ROOM(5L),
    RETURNING_ROOM(6L),
    RETURNING_SUBSCRIPTION(7L),
    RENEW(8L);
    private final Long id;

    Status(Long id) {
        this.id = id;
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
