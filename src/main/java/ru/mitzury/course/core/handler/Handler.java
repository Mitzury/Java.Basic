package ru.mitzury.course.core.handler;

import ru.mitzury.course.core.dto.MessageRequest;
import ru.mitzury.course.core.http.Response;

public interface Handler {
    void handle(MessageRequest.Message message, Response resp) throws Exception;
}