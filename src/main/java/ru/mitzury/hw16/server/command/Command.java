package ru.mitzury.hw16.server.command;

import ru.mitzury.hw16.server.ChatUser;

public interface Command {
    String name();
    void execute(ChatUser sender, String[] args);
}