package ru.mitzury.hw16.server.command;


import ru.mitzury.hw16.server.ChatUser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandParser {

    private static final Map<String, Command> commands = new HashMap<>();

    static {
        register(new WhisperCommand());
        register(new KickCommand());
    }

    private static void register(Command cmd) {
        commands.put(cmd.name(), cmd);
    }

    public static boolean handle(ChatUser sender, String message) {

        if (!message.startsWith("/"))
            return false;

        String[] parts = message.substring(1).split(" ");
        String name = parts[0];

        Command cmd = commands.get(name);
        if (cmd == null) {
            sender.getHandler().send("Неизвестная команда");
            return true;
        }

        String[] args = Arrays.copyOfRange(parts, 1, parts.length);
        cmd.execute(sender, args);
        return true;
    }
}