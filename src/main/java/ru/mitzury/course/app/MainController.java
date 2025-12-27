package ru.mitzury.course.app;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.mitzury.course.core.BaseController;

import java.io.IOException;

public class MainController extends BaseController {

    @Override
    protected void doHandle(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        render(resp, "Main");
    }
}