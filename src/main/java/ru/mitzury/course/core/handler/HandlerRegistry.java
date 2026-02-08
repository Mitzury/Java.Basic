package ru.mitzury.course.core.handler;

import java.util.Map;

public final class HandlerRegistry {

    private final Map<String, Handler> handlers;

    public HandlerRegistry(Map<String, Handler> handlers) {
        this.handlers = Map.copyOf(handlers);
    }

    public Handler resolve(String rawPath) {
        if (rawPath == null || rawPath.isBlank()) {
            throw new IllegalArgumentException("Path is empty");
        }

        String path = normalize(rawPath);

        Handler handler = handlers.get(path);
        if (handler == null) {
            throw new HandlerNotFoundException(path);
        }

        return handler;
    }

    private String normalize(String path) {
        if (path.endsWith("/")) {
            return path.substring(0, path.length() - 1);
        }
        return path;
    }
    public class HandlerNotFoundException extends RuntimeException {

        public HandlerNotFoundException(String path) {
            super("Handler not found for path: " + path);
        }
    }
}
