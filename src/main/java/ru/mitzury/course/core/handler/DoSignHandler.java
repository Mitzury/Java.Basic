package ru.mitzury.course.core.handler;

import ru.mitzury.course.app.DoSign;
import ru.mitzury.course.core.dto.MessageRequest;
import ru.mitzury.course.core.http.Response;

public class DoSignHandler implements Handler {

    private final DoSign app = new DoSign();

    @Override
    public void handle(MessageRequest.Message message, Response resp) throws Exception {

        String result = app.sign(message.data);

        resp.ok(result);
    }
}