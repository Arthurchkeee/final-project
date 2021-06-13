package com.epam.project.command;

public interface CommandResponse {

    default boolean isRedirect() {
        return false;
    }
    Destination getDestination();

}
