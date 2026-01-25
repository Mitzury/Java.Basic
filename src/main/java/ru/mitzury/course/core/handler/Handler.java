package ru.mitzury.course.core.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.mitzury.course.core.dto.RequestDTO;


public interface Handler {
    void handle(RequestDTO dto, HttpServletRequest request, HttpServletResponse response) throws Exception;
}