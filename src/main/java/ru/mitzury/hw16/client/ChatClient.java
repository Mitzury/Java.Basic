package ru.mitzury.hw16.client;


import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 2288)) {

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            new Thread(new ServerListener(in)).start();

            Scanner scanner = new Scanner(System.in);
            while (true) {
                out.println(scanner.nextLine());
            }

        } catch (IOException e) {
            System.out.println("Нет соединения с сервером");
        }
    }
}