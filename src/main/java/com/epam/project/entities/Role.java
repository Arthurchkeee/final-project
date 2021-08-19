package com.epam.project.entities;

public enum Role implements BaseEntity {
    ADMIN(1L),
    LIBRARIAN(2L),
    READER(3L),
    ANONYMOUS(4L);

    private final Long id;

    Role(Long id) {
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
