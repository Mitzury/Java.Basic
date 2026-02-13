package ru.mitzury.hw16.server;

public class ChatUser {

    private final String name;
    private final UserRole role;
    private final ClientHandler handler;

    public ChatUser(String name, UserRole role, ClientHandler handler) {
        this.name = name;
        this.role = role;
        this.handler = handler;
    }

    public String getName() { return name; }
    public UserRole getRole() { return role; }
    public ClientHandler getHandler() { return handler; }

    public boolean isAdmin() {
        return role == UserRole.ADMIN;
    }
}
