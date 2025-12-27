package ru.mitzury.course.core;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class Router {

    public enum HttpMethod {
        GET, POST, PUT, DELETE
    }

    private final Map<String, Map<HttpMethod, BaseController>> routes = new HashMap<>();

    public void addRoute(HttpMethod method, String path, BaseController controller) {
        routes
                .computeIfAbsent(path, p -> new EnumMap<>(HttpMethod.class))
                .put(method, controller);
    }

    public void dispatch(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String path = req.getRequestURI();

        HttpMethod method;
        try {
            method = HttpMethod.valueOf(req.getMethod());
        } catch (IllegalArgumentException e) {
            writeError(resp, HttpServletResponse.SC_METHOD_NOT_ALLOWED,
                    "405 Method Not Allowed");
            return;
        }

        Map<HttpMethod, BaseController> methodMap = routes.get(path);
        if (methodMap == null) {
            writeError(resp, HttpServletResponse.SC_NOT_FOUND,
                    "404 Not Found");
            return;
        }

        BaseController controller = methodMap.get(method);
        if (controller == null) {
            writeError(resp, HttpServletResponse.SC_METHOD_NOT_ALLOWED,
                    "405 Method Not Allowed");
            return;
        }

        try {
            controller.handle(req, resp);
        } catch (Exception e) {
            handleException(resp, e);
        }
    }

    private void handleException(HttpServletResponse resp, Exception e)
            throws IOException {

        writeError(resp,
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                "500 Internal Server Error");
    }

    private void writeError(HttpServletResponse resp, int status, String message)
            throws IOException {

        resp.reset();
        resp.setStatus(status);
        resp.setContentType("text/plain; charset=UTF-8");
        resp.getWriter().write(message);
    }
}
