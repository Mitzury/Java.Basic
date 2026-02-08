package ru.mitzury.course.core.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.mitzury.course.app.DoCreateTitle.DoCreateTitleService;
import ru.mitzury.course.core.dto.DoCreateTitleCommand;

import java.io.IOException;

public class DoCreateTitleHandler implements Handler {

    private static final String MSG_NAME = "DoCreateTitle";

    private final CommandHandlerExecutor executor;
    private final DoCreateTitleService service;

    public DoCreateTitleHandler(
            CommandHandlerExecutor executor,
            DoCreateTitleService service
    ) {
        this.executor = executor;
        this.service = service;
    }

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        executor.execute(
                request,
                response,
                MSG_NAME,
                DoCreateTitleCommand.class,
                service::execute,
                "Title page created successfully"
        );
    }
}
