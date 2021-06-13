package com.epam.project.command;

public interface Command {

    CommandResponse execute(CommandRequest request);

    static Command of(String commandName) {
        return CommandInstance.commandOf(commandName);
    }
}
