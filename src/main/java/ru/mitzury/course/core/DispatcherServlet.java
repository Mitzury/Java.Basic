package ru.mitzury.course.core;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.mitzury.course.app.MainController;

import java.io.IOException;

public class DispatcherServlet extends HttpServlet {

    private final Router router = new Router();

    @Override
    public void init() {
        router.addRoute(Router.HttpMethod.GET, "/", new MainController());
        router.addRoute(Router.HttpMethod.GET, "/hello", new MainController());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        router.dispatch(req, resp);
    }
}