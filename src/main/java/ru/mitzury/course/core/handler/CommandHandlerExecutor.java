package ru.mitzury.course.core.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.mitzury.course.core.dto.MessageDTO;
import ru.mitzury.course.core.dto.RequestDTO;
import ru.mitzury.course.core.http.HttpResponseWriter;

import java.io.IOException;

public final class CommandHandlerExecutor {

    private final ObjectMapper objectMapper;
    private final HttpResponseWriter responseWriter;

    public CommandHandlerExecutor(
            ObjectMapper objectMapper,
            HttpResponseWriter responseWriter
    ) {
        this.objectMapper = objectMapper;
        this.responseWriter = responseWriter;
    }

    public <T> void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            String expectedMsgName,
            Class<T> commandType,
            CommandProcessor<T> processor,
            String successMessage
    ) throws IOException {

        RequestDTO dto;
        try {
            dto = objectMapper.readValue(request.getInputStream(), RequestDTO.class);
        } catch (Exception e) {
            responseWriter.writeError(response, 400, "INVALID_REQUEST");
            return;
        }

        MessageDTO message = dto.firstMessage();


        if (!expectedMsgName.equals(message.msgName())) {
            responseWriter.writeError(response, 400, "INVALID_MSG_NAME");
            return;
        }

        T command;
        try {
            command = objectMapper.treeToValue(
                    message.payload(),
                    commandType
            );
        } catch (Exception e) {
            responseWriter.writeError(response, 400, "INVALID_PAYLOAD");
            return;
        }

        try {
            processor.process(command);
        } catch (Exception e) {
            responseWriter.writeError(response, 500, "EXECUTION_ERROR");
            return;
        }

        responseWriter.writeSuccess(response, successMessage);
    }

    @FunctionalInterface
    public interface CommandProcessor<T> {

        void process(T command) throws Exception;
    }
}
