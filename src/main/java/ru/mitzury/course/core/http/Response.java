package ru.mitzury.course.core.http;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class Response {

    private final HttpServletResponse raw;

    public Response(HttpServletResponse raw) {
        this.raw = raw;
    }

    public void status(int status) {
        raw.setStatus(status);
    }

    public void json(String body) throws IOException {
        raw.setContentType("application/json");
        raw.getWriter().write(body);
    }

    public void text(String body) throws IOException {
        raw.setContentType("text/plain");
        raw.getWriter().write(body);
    }

    public void ok(String result) throws IOException {
        raw.setStatus(HttpServletResponse.SC_OK);
        json("""
            {
              "result": "%s"
            }
            """.formatted(result));
    }

    public void badRequest(String message) throws IOException {
        raw.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        json("""
            { "error": "%s" }
            """.formatted(message));
    }

    public void methodNotAllowed() throws IOException {
        status(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        json("""
            {
              "error": "Method Not Allowed"
            }
            """);
    }
    public void internalError() throws IOException {
        raw.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        json("""
            { "error": "Internal Server Error" }
            """);
    }

}