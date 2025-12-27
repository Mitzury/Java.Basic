package ru.mitzury.course.core;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {

    private final Router router = new Router();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        router.dispatch(req, resp);
    }
}