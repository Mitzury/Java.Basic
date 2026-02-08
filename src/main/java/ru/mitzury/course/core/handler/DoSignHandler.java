package ru.mitzury.course.core.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.mitzury.course.app.DoSign.DoSignService;
import ru.mitzury.course.core.dto.DoSignCommand;

import java.io.IOException;

public class DoSignHandler implements Handler {

    private static final String MSG_NAME = "DoSign";

    private final CommandHandlerExecutor executor;
    private final DoSignService service;

    public DoSignHandler(
            CommandHandlerExecutor executor,
            DoSignService service
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
                DoSignCommand.class,
                service::execute,
                "PDF signed successfully"
        );
    }
}
