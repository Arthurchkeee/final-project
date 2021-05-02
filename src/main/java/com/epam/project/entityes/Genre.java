package com.epam.project.entityes;

public enum Genre implements BaseEntity{
    COMEDY(1L),
    HORROR(2L),
    DRAMA(3L),
    DETECTIVE(4L),
    ADULT(5L)
    ;
    private final Long ID;

    Genre(Long id) {
        this.ID=id;
    }

    @Override
    public Long getId() {
        return ID;
    }

    @Override
    public String getName() {
        return this.name();
    }
}
