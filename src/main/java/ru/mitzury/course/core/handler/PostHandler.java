package ru.mitzury.course.core.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface PostHandler {
    void handle(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
