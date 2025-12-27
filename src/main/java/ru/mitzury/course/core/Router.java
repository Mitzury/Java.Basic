package ru.mitzury.course.core;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.mitzury.course.app.MainController;

import java.io.IOException;

public class Router {

    private final MainController MainController = new MainController();

    public void dispatch(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String path = req.getRequestURI();

        switch (path) {
            case "/":
            case "/hello":
                MainController.handle(req, resp);
                break;

            default:
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("404 Not Found");
        }
    }
}