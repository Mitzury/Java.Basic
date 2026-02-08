package ru.mitzury.course.server;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mitzury.course.config.AppConfig;
import ru.mitzury.course.config.HandlerConfiguration;
import ru.mitzury.course.servlet.DoFileWorkerServlet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class TomcatServer {

    private static final Logger log = LoggerFactory.getLogger(TomcatServer.class);

    private final Tomcat tomcat;
    private Path baseDir;

    public TomcatServer() {
        this.tomcat = new Tomcat();
    }

    public void start() throws IOException, LifecycleException {
        configureTomcat();

        Context context = tomcat.addContext(
                AppConfig.CONTEXT_PATH,
                baseDir.toString()
        );

        DoFileWorkerServlet servlet =
                new DoFileWorkerServlet(HandlerConfiguration.handlerRegistry());

        Tomcat.addServlet(context, AppConfig.SERVLET_NAME, servlet)
                .setLoadOnStartup(1);

        context.addServletMappingDecoded(
                AppConfig.SERVLET_MAPPING,
                AppConfig.SERVLET_NAME
        );

        tomcat.start();
        log.info("Tomcat started on {}:{}", AppConfig.HOST, AppConfig.PORT);
    }

    public void await() {
        tomcat.getServer().await();
    }

    public void stop() {
        try {
            tomcat.stop();
            tomcat.destroy();
            cleanup();
        } catch (LifecycleException e) {
            log.warn("Error during Tomcat shutdown", e);
        }
    }

    private void configureTomcat() throws IOException {
        tomcat.setPort(AppConfig.PORT);
        tomcat.setHostname(AppConfig.HOST);
        tomcat.getConnector(); // force connector init

        baseDir = Files.createTempDirectory("tomcat-");
        tomcat.setBaseDir(baseDir.toString());

        log.debug("Tomcat baseDir: {}", baseDir);
    }

    private void cleanup() {
        if (baseDir != null) {
            try {
                Files.walk(baseDir)
                        .sorted((a, b) -> b.compareTo(a))
                        .forEach(path -> {
                            try {
                                Files.deleteIfExists(path);
                            } catch (IOException ignored) {
                            }
                        });
            } catch (IOException e) {
                log.warn("Failed to cleanup Tomcat baseDir", e);
            }
        }
    }
}
