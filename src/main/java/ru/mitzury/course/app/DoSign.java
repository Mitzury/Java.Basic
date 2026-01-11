package ru.mitzury.course.app;

import java.util.Map;

public class DoSign {

    public static Object handle(Map<String, Object> data) {
        String user = (String) data.get("user");
        String password = (String) data.get("password");

        if (user == null || password == null) {
            throw new IllegalArgumentException("user and password required");
        }

        if ("ivan".equals(user) && "123".equals(password)) {
            return Map.of(
                    "token", "abc123",
                    "expires", 3600
            );
        }

        throw new IllegalArgumentException("Invalid credentials");
    }
}
