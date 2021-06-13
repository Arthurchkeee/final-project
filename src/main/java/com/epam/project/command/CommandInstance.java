package com.epam.project.command;

public enum CommandInstance {
    SHOW(new ShowMainPageCommand()),
    SHOW_LOGIN(new ShowLoginPageCommand()),
    LOGIN(new UserLoginCommand());
    //SHOW_PAGE(new ShowPagePageCommand());

    private Command command;

    CommandInstance(Command command) {
        this.command = command;
    }

    static Command commandOf(String name) {
        for (CommandInstance value : values()) {
            if (value.name().equalsIgnoreCase(name)) {
                return value.command;
            }
        }
        return SHOW.command;
    }
}
