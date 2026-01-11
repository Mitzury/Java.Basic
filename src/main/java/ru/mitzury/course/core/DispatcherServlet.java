package ru.mitzury.course.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.mitzury.course.core.dto.Request;
import ru.mitzury.course.core.dto.Response;
import ru.mitzury.course.app.DoSign;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DispatcherServlet extends HttpServlet {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try {
            Request request = mapper.readValue(req.getInputStream(), Request.class);
            request.validate();

            Object result = dispatch(request);
            Response<?> response = Response.ok(result);

            mapper.writeValue(resp.getOutputStream(), response);

        } catch (IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            mapper.writeValue(resp.getOutputStream(), Response.error(e.getMessage()));
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            mapper.writeValue(resp.getOutputStream(), Response.error("Internal error"));
        }
    }

    private Object dispatch(Request request) {
        return switch (request.getMSG().name) {
            case "doSign" -> DoSign.handle(request.getMSG().data);
            default -> throw new IllegalArgumentException(
                    "Unknown MSG.NAME: " + request.getMSG().name
            );
        };
    }
}
