package ru.mitzury.hw13.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class CalculatorClient {

    private static final String HOST = "localhost";
    private static final int PORT = 5000;

    public static void main(String[] args) {
        try (
                Socket socket = new Socket(HOST, PORT);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(
                        new BufferedWriter(
                                new OutputStreamWriter(socket.getOutputStream())),
                        true);
                Scanner scanner = new Scanner(System.in)
        ) {


            System.out.println(in.readLine());

            System.out.print("Введите выражение (например: 5 + 3): ");
            String input = scanner.nextLine();

            out.println(input);

            // получаем результат
            System.out.println(in.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
