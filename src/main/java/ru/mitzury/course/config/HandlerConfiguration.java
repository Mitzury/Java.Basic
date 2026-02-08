package ru.mitzury.course.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ru.mitzury.course.app.DoCreateTitle.DoCreateTitleService;
import ru.mitzury.course.app.DoSign.DoSignService;
import ru.mitzury.course.core.handler.CommandHandlerExecutor;
import ru.mitzury.course.core.handler.DoCreateTitleHandler;
import ru.mitzury.course.core.handler.DoSignHandler;
import ru.mitzury.course.core.handler.HandlerRegistry;
import ru.mitzury.course.core.http.HttpResponseWriter;

import java.util.Map;

public final class HandlerConfiguration {

    public static HandlerRegistry handlerRegistry() {

        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());

        HttpResponseWriter responseWriter =
                new HttpResponseWriter(objectMapper);

        CommandHandlerExecutor executor =
                new CommandHandlerExecutor(objectMapper, responseWriter);

        DoSignService doSignService = new DoSignService();
        DoCreateTitleService doCreateTitleService = new DoCreateTitleService();

        return new HandlerRegistry(
                Map.of(
                        "/DoSign",
                        new DoSignHandler(executor, doSignService),

                        "/DoCreateTitle",
                        new DoCreateTitleHandler(executor, doCreateTitleService)
                )
        );
    }
}
