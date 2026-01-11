package ru.mitzury.course.core;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.mitzury.course.core.dto.MessageRequest;
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
            MessageRequest body = request.json(MessageRequest.class);

            if (body.messages == null || body.messages.isEmpty()) {
                response.badRequest("msg is empty");
                return;
            }

            MessageRequest.Message message = body.messages.get(0);
            Handler handler = handlers.get(message.msgName);

            if (handler == null) {
                response.badRequest("Unknown MsgName: " + message.msgName);
                return;
            }

            handler.handle(message, response);

        } catch (BadRequestException e) {
            response.badRequest(e.getMessage());
        } catch (Exception e) {
            response.internalError();
        }

    }
}
