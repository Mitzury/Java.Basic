package ru.mitzury.course.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mitzury.course.core.handler.Handler;
import ru.mitzury.course.core.handler.HandlerRegistry;

import java.io.IOException;

public final class DoFileWorkerServlet extends HttpServlet {

    private static final Logger log =
            LoggerFactory.getLogger(DoFileWorkerServlet.class);

    private final HandlerRegistry registry;

    public DoFileWorkerServlet(HandlerRegistry registry) {
        this.registry = registry;
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        try {
            Handler handler = registry.resolve(request.getPathInfo());
            handler.handle(request, response);

        } catch (HandlerRegistry.HandlerNotFoundException e) {
            log.debug("Handler not found: {}", request.getPathInfo());
            response.sendError(HttpServletResponse.SC_NOT_FOUND);

        } catch (IllegalArgumentException e) {
            log.warn("Bad request: {}", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());

        } catch (Exception e) {
            log.error("Internal server error", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
