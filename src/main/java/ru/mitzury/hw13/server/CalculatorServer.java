package ru.mitzury.hw13.server;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculatorServer {

    private static final int PORT = 5000;

    public static void main(String[] args) {
        System.out.println("Сервер запущен на порту " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket client = serverSocket.accept();
                new Thread(() -> handleClient(client)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket client) {
        try (
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(client.getInputStream()));
                PrintWriter out = new PrintWriter(
                        new BufferedWriter(
                                new OutputStreamWriter(client.getOutputStream())),
                        true)
        ) {

            out.println("Доступные операции: +  -  *  /");

            String request = in.readLine();
            String[] parts = request.split(" ");

            double a = Double.parseDouble(parts[0]);
            String op = parts[1];
            double b = Double.parseDouble(parts[2]);

            double result;

            switch (op) {
                case "+" -> result = a + b;
                case "-" -> result = a - b;
                case "*" -> result = a * b;
                case "/" -> {
                    if (b == 0) {
                        out.println("Ошибка: деление на ноль");
                        return;
                    }
                    result = a / b;
                }
                default -> {
                    out.println("Неизвестная операция");
                    return;
                }
            }

            out.println("Результат: " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
