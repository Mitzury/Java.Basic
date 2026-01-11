package ru.mitzury.course.core.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EchoHandler implements PostHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String body = request.getReader().lines().reduce("", String::concat);

        response.setContentType("application/json");
        response.getWriter().write("{\"echo\":" + body + "}");
    }
}
