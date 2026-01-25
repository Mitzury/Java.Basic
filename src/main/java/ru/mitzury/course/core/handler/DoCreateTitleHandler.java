package ru.mitzury.course.core.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.mitzury.course.app.DoCreateTitle.DoCreateTitleService;
import ru.mitzury.course.core.dto.DoCreateTitleCommand;
import ru.mitzury.course.core.dto.MessageDTO;
import ru.mitzury.course.core.dto.RequestDTO;

import java.io.IOException;
import java.io.PrintWriter;

public class DoCreateTitleHandler implements Handler {

    private final DoCreateTitleService service = new DoCreateTitleService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(RequestDTO dto,
                       HttpServletRequest request,
                       HttpServletResponse response) throws Exception {

        if (dto.getMsg() == null || dto.getMsg().isEmpty()) {
            sendError(response, HttpServletResponse.SC_BAD_REQUEST, "EMPTY_MSG");
            return;
        }

        MessageDTO message = dto.getMsg().get(0);

        if (!"DoCreateTitle".equals(message.getMsgName())) {
            sendError(response, HttpServletResponse.SC_BAD_REQUEST, "INVALID_MSG_NAME");
            return;
        }

        DoCreateTitleCommand command = objectMapper.treeToValue(
                message.getPayload(),
                DoCreateTitleCommand.class
        );

        service.execute(command);

        response.setStatus(HttpServletResponse.SC_OK);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        try (PrintWriter writer = response.getWriter()) {
            writer.write("{\"result\":\"Title page created successfully\"}");
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