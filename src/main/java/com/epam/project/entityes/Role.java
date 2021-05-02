package com.epam.project.entityes;

public enum Role implements BaseEntity{
    ADMIN(1L),
    LIBRERIAN(2L),
    READER(3L)
    ;

    private final Long id;

    Role(Long id) {
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
