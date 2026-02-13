package ru.mitzury.hw16.server;

import ru.mitzury.hw16.server.command.CommandParser;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private ChatUser user;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            // регистрация
            while (true) {
                out.println("Введите ник:");
                String name = in.readLine();
                if (name == null) return;

                if (!ChatServer.users.containsKey(name)) {

                    UserRole role = name.equalsIgnoreCase("admin")
                            ? UserRole.ADMIN
                            : UserRole.USER;

                    user = new ChatUser(name, role, this);
                    ChatServer.users.put(name, user);
                    break;

                } else {
                    out.println("Ник занят!");
                }
            }

            ChatServer.broadcast(user.getName() + " вошёл в чат", user.getName());

            String message;
            while ((message = in.readLine()) != null) {

                if (CommandParser.handle(user, message))
                    continue;

                ChatServer.broadcast(user.getName() + ": " + message, user.getName());
            }

        } catch (IOException ignored) {
        } finally {
            if (user != null) {
                ChatServer.removeUser(user.getName());
                ChatServer.broadcast(user.getName() + " покинул чат", user.getName());
            }
            disconnect();
        }
    }

    public void send(String msg) {
        out.println(msg);
    }

    public void disconnect() {
        try { socket.close(); } catch (IOException ignored) {}
    }
}
