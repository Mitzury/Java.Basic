package ru.mitzury.course.core;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.mitzury.course.core.handler.*;

import java.util.Map;

public class DispatcherServlet extends HttpServlet {

    private Map<String, PostHandler> handlers;

    @Override
    public void init() {
        handlers = Map.of(
                "/echo", new EchoHandler()
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String path = req.getPathInfo(); // /ping, /echo

            PostHandler handler = handlers.get(path);
            if (handler == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Unknown POST endpoint");
                return;
            }

            handler.handle(req, resp);

        } catch (Exception e) {
            throw new RuntimeException("POST handling failed", e);
        }
    }
}
