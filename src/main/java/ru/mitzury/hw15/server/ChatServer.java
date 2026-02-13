package ru.mitzury.hw15.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChatServer {

    private static final int PORT = 5000;

    // клиенты по нику
    public static Map<String, ClientHandler> clients = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        System.out.println("Сервер запущен на порту " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Новое подключение: " + socket.getInetAddress());

                new Thread(new ClientHandler(socket)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // широковещательное сообщение
    public static void broadcast(String message, String sender) {
        for (ClientHandler client : clients.values()) {
            if (!client.getName().equals(sender)) {
                client.send(message);
            }
        }
    }

    // личное сообщение
    public static boolean whisper(String to, String message, String from) {
        ClientHandler target = clients.get(to);
        ClientHandler sender = clients.get(from);

        if (target == null) return false;

        target.send("[ЛС от " + from + "] " + message);
        sender.send("[ЛС для " + to + "] " + message);
        return true;
    }
}
