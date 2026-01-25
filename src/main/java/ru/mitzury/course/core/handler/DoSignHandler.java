package ru.mitzury.course.core.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ru.mitzury.course.app.DoSign.DoSignService;
import ru.mitzury.course.core.dto.DoSignCommand;
import ru.mitzury.course.core.dto.MessageDTO;
import ru.mitzury.course.core.dto.RequestDTO;

import java.io.IOException;
import java.io.PrintWriter;

public class DoSignHandler implements Handler {

    private final DoSignService doSignService = new DoSignService();
    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());


    @Override
    public void handle(RequestDTO dto, HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (dto.getMsg() == null || dto.getMsg().isEmpty()) {
            sendError(response, HttpServletResponse.SC_BAD_REQUEST, "EMPTY_MSG");
            return;
        }
        MessageDTO message = dto.getMsg().get(0);
        if (!"DoSign".equals(message.getMsgName())) {
            sendError(response, HttpServletResponse.SC_BAD_REQUEST, "INVALID_MSG_NAME");
            return;
        }

        DoSignCommand command = objectMapper.treeToValue(
                message.getPayload(),
                DoSignCommand.class
        );

        // 3. Вызов use case
        doSignService.execute(command);


        response.setStatus(HttpServletResponse.SC_OK);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        try (PrintWriter writer = response.getWriter()) {
            writer.write("{\"result\":\"PDF signed successfully\"}");
        }
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
