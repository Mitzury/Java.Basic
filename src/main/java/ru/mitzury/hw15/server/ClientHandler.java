package ru.mitzury.hw15.server;


import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String name;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            // ввод имени
            while (true) {
                out.println("Введите ник:");
                name = in.readLine();

                if (name == null) return;

                if (!ChatServer.clients.containsKey(name)) {
                    ChatServer.clients.put(name, this);
                    break;
                } else {
                    out.println("Ник уже занят!");
                }
            }

            ChatServer.broadcast(name + " вошёл в чат", name);

            String message;
            while ((message = in.readLine()) != null) {

                // ====== команда /w ======
                if (message.startsWith("/w ")) {
                    handleWhisper(message);
                    continue;
                }

                // обычное сообщение
                System.out.println(name + ": " + message);
                ChatServer.broadcast(name + ": " + message, name);
            }

        } catch (IOException e) {
            System.out.println(name + " отключился");
        } finally {
            ChatServer.clients.remove(name);
            ChatServer.broadcast(name + " покинул чат", name);
            try { socket.close(); } catch (IOException ignored) {}
        }
    }

    private void handleWhisper(String msg) {
        String[] parts = msg.split(" ", 3);

        if (parts.length < 3) {
            out.println("Использование: /w <nick> <message>");
            return;
        }

        String target = parts[1];
        String text = parts[2];

        boolean ok = ChatServer.whisper(target, text, name);
        if (!ok) {
            out.println("Пользователь не найден");
        }
    }

    public void send(String msg) {
        out.println(msg);
    }
}