package ru.mitzury.course.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.mitzury.course.core.dto.RequestDTO;
import ru.mitzury.course.core.handler.DoCreateTitleHandler;
import ru.mitzury.course.core.handler.DoSignHandler;
import ru.mitzury.course.core.handler.Handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class DoFileWorkerServlet extends HttpServlet {

    private final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    private final Map<String, Handler> handlers = new HashMap<>();

    @Override
    public void init() {
        handlers.put("/DoSign", new DoSignHandler());
        handlers.put("/DoCreateTitle", new DoCreateTitleHandler());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String path = request.getPathInfo();

        if (path == null || !handlers.containsKey(path)) {
            sendError(response, HttpServletResponse.SC_NOT_FOUND, "NOT_FOUND");
            return;
        }

        RequestDTO dto;
        try (BufferedReader reader = request.getReader()) {
            dto = mapper.readValue(reader, RequestDTO.class);
        } catch (Exception e) {
            sendError(response, HttpServletResponse.SC_BAD_REQUEST, "INVALID_JSON");
            return;
        }
        try {
            handlers.get(path).handle(dto, request, response);
        } catch (Exception e) {
            sendError(response,
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        sendError(response, HttpServletResponse.SC_METHOD_NOT_ALLOWED, "METHOD_NOT_ALLOWED");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
         sendError(response, HttpServletResponse.SC_METHOD_NOT_ALLOWED, "METHOD_NOT_ALLOWED");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        sendError(response, HttpServletResponse.SC_METHOD_NOT_ALLOWED, "METHOD_NOT_ALLOWED");
    }


    private void sendError(HttpServletResponse response, int status, String error) throws IOException {
        response.setStatus(status);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        try (PrintWriter writer = response.getWriter()) {
            writer.write("{\"error\":\"" + error + "\"}");
        }
    }
}
