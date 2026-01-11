package ru.mitzury.course.core;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.mitzury.course.core.handler.DoSignHandler;
import ru.mitzury.course.core.handler.Handler;
import ru.mitzury.course.core.http.Request;
import ru.mitzury.course.core.http.Response;

import java.io.IOException;
import java.util.Map;

public class DispatcherServlet extends HttpServlet {

    private final Map<String, Handler> handlers = Map.of(
            "DoSign", new DoSignHandler()
    );

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        Request request = new Request(req);
        Response response = new Response(resp);

        if (!"POST".equalsIgnoreCase(request.method())) {
            response.methodNotAllowed();
            return;
        }

        try {
            JsonNode root = request.jsonTree();

            JsonNode msgArray = root.get("msg");
            if (msgArray == null || !msgArray.isArray() || msgArray.isEmpty()) {
                response.badRequest("msg is empty");
                return;
            }

            JsonNode firstMsg = msgArray.get(0);
            JsonNode msgNameNode = firstMsg.get("MsgName");

            if (msgNameNode == null || !msgNameNode.isTextual()) {
                response.badRequest("MsgName is required");
                return;
            }

            String msgName = msgNameNode.asText();
            Handler handler = handlers.get(msgName);

            if (handler == null) {
                response.badRequest("Unknown MsgName: " + msgName);
                return;
            }

            handler.handle(request, response);

        } catch (BadRequestException e) {
            response.badRequest(e.getMessage());
        } catch (Exception e) {
            response.internalError();
        }
    }
}
