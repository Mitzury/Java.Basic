package ru.mitzury.course.core.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface Handler {

    void handle(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException;
}
