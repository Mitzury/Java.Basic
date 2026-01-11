package ru.mitzury.course.core.handler;

import ru.mitzury.course.core.http.Request;
import ru.mitzury.course.core.http.Response;

public interface Handler {
    void handle(Request req, Response resp) throws Exception;
}