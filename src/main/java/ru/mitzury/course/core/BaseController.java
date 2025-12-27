package ru.mitzury.course.core;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseController {

    protected void prepare(HttpServletResponse resp) {
        resp.setCharacterEncoding("UTF-8");
    }

    public final void handle(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        prepare(resp);
        doHandle(req, resp);
    }

    protected void render(HttpServletResponse resp, String view)
            throws IOException {

        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().write(ViewRenderer.render(view));
    }
    protected abstract void doHandle(HttpServletRequest req, HttpServletResponse resp)
            throws IOException;
}