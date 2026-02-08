package ru.mitzury.course.core.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public final class HttpResponseWriter {

    private final ObjectMapper objectMapper;

    public HttpResponseWriter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void write(
            HttpServletResponse response,
            int status,
            String message
    ) throws IOException {

        response.setStatus(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        objectMapper.writeValue(
                response.getWriter(),
                new Response(message)
        );
    }

    public void writeSuccess(
            HttpServletResponse response,
            String message
    ) throws IOException {
        write(response, HttpServletResponse.SC_OK, message);
    }

    public void writeError(
            HttpServletResponse response,
            int status,
            String message
    ) throws IOException {
        write(response, status, message);
    }
}
