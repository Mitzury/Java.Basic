package ru.mitzury.course;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;
import ru.mitzury.course.server.TomcatServer;

public final class MyApp {

    private static final Logger log = LoggerFactory.getLogger(MyApp.class);

    public static void main(String[] args) {

        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();

        TomcatServer server = new TomcatServer();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("Shutting down application");
            server.stop();
        }));

        try {
            server.start();
            log.info("Application started successfully");
            server.await();
        } catch (Exception e) {
            log.error("Failed to start application", e);
            System.exit(1);
        }
    }
}
