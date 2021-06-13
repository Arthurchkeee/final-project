package com.epam.project.command;

public enum ServletDestination  implements Destination {

    MAIN_PAGE("/WEB-INF/jsp/main.jsp"),
    LOGIN_PAGE("/WEB-INF/jsp/login.jsp"),
    PAGE_PAGE("/WEB-INF/jsp/page.jsp"),
    INDEX("/");

    private final String path;

    ServletDestination(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
