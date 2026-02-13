package ru.mitzury.hw16.server.command;

import ru.mitzury.hw16.server.ChatServer;
import ru.mitzury.hw16.server.ChatUser;

import java.util.Arrays;

public class WhisperCommand implements Command {

    @Override
    public String name() {
        return "w";
    }

    @Override
    public void execute(ChatUser sender, String[] args) {

        if (args.length < 2) {
            sender.getHandler().send("Использование: /w <nick> <msg>");
            return;
        }

        String targetName = args[0];
        String msg = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

        ChatUser target = ChatServer.getUser(targetName);

        if (target == null) {
            sender.getHandler().send("Пользователь не найден");
            return;
        }

        target.getHandler().send("[ЛС от " + sender.getName() + "] " + msg);
        sender.getHandler().send("[ЛС для " + target.getName() + "] " + msg);
    }
}
