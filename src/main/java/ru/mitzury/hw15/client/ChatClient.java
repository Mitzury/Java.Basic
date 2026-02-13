package ru.mitzury.hw15.client;

import ru.mitzury.hw16.client.ServerListener;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

    private static final String HOST = "localhost";
    private static final int PORT = 2288;

    public static void main(String[] args) {

        try (Socket socket = new Socket(HOST, PORT)) {

            System.out.println("Подключено к серверу");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            // поток чтения сообщений от сервера
            new Thread(new ServerListener(in)).start();

            // ввод пользователя
            while (true) {
                String message = scanner.nextLine();
                out.println(message);
            }

        } catch (IOException e) {
            System.out.println("Не удалось подключиться к серверу");
        }
    }
}
