package ru.mitzury.hw16.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChatServer {

    private static final int PORT = 2288;

    public static Map<String, ChatUser> users = new ConcurrentHashMap<>();

    public static void main(String[] args) {

        System.out.println("Сервер запущен на порту " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ClientHandler(socket)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ChatUser getUser(String name) {
        return users.get(name);
    }

    public static void removeUser(String name) {
        users.remove(name);
    }

    public static void broadcast(String message, String except) {
        for (ChatUser user : users.values()) {
            if (!user.getName().equals(except)) {
                user.getHandler().send(message);
            }
        }
    }
}