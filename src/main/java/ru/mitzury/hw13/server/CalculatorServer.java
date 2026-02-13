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
                System.out.println("Подключился клиент: " + client.getInetAddress());
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

            out.println("=== КАЛЬКУЛЯТОР ===");
            out.println("Формат: <число> <операция> <число>");
            out.println("Доступные операции: +  -  *  /");
            out.println("Для выхода введите: exit");

            while (true) {

                String request = in.readLine();

                // клиент отключился
                if (request == null) {
                    break;
                }

                // выход
                if (request.equalsIgnoreCase("exit")) {
                    out.println("Соединение закрыто");
                    break;
                }

                // пустая строка
                if (request.isBlank()) {
                    out.println("Пустой запрос");
                    continue;
                }

                String[] parts = request.trim().split("\\s+");

                // неверный формат
                if (parts.length != 3) {
                    out.println("Ошибка формата. Пример: 2 + 2");
                    continue;
                }

                try {
                    double a = Double.parseDouble(parts[0]);
                    double b = Double.parseDouble(parts[2]);
                    String op = parts[1];

                    double result = calculate(a, b, op);

                    out.println("Результат: " + result);

                } catch (NumberFormatException e) {
                    out.println("Ошибка: введены не числа");
                } catch (ArithmeticException | IllegalArgumentException e) {
                    out.println(e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println("Клиент отключился: " + client.getInetAddress());
        } finally {
            try {
                client.close();
            } catch (IOException ignored) {}
            System.out.println("Соединение закрыто: " + client.getInetAddress());
        }
    }

    /**
     * Логика калькулятора (вынесена отдельно)
     */
    private static double calculate(double a, double b, String op) {

        return switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> {
                if (b == 0)
                    throw new ArithmeticException("Ошибка: деление на ноль");
                yield a / b;
            }
            default -> throw new IllegalArgumentException("Неизвестная операция");
        };
    }
}