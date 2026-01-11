package ru.mitzury.course.core.handler;

import com.fasterxml.jackson.databind.JsonNode;
import ru.mitzury.course.app.DoSign;
import ru.mitzury.course.core.BadRequestException;
import ru.mitzury.course.core.http.Request;
import ru.mitzury.course.core.http.Response;

public class DoSignHandler implements Handler {

    private final DoSign app = new DoSign();

    @Override
    public void handle(Request req, Response resp) throws Exception {

        JsonNode msg = req.jsonTree().get("msg").get(0);
        JsonNode dataNode = msg.get("data");

        if (dataNode == null || !dataNode.isTextual()) {
            throw new BadRequestException("Field 'data' is required for DoSign");
        }


        resp.ok(app.sign(dataNode.asText()));
    }
}
