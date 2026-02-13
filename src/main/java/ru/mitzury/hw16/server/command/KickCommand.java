package ru.mitzury.hw16.server.command;


import ru.mitzury.hw16.server.ChatServer;
import ru.mitzury.hw16.server.ChatUser;

public class KickCommand implements Command {

    @Override
    public String name() {
        return "kick";
    }

    @Override
    public void execute(ChatUser sender, String[] args) {

        if (!sender.isAdmin()) {
            sender.getHandler().send("Только ADMIN может использовать /kick");
            return;
        }

        if (args.length < 1) {
            sender.getHandler().send("Использование: /kick <nick>");
            return;
        }

        String targetName = args[0];
        ChatUser target = ChatServer.getUser(targetName);

        if (target == null) {
            sender.getHandler().send("Пользователь не найден");
            return;
        }

        target.getHandler().send("Вас отключил администратор");
        target.getHandler().disconnect();

        ChatServer.removeUser(targetName);
        ChatServer.broadcast(targetName + " был кикнут администратором", sender.getName());
    }
}