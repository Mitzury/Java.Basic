package ru.mitzury.course;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import ru.mitzury.course.core.DoFileWorkerServlet;

import java.nio.file.Files;
import java.nio.file.Path;

public class MyApp {

    public static void main(String[] args) throws Exception {

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.setHostname("127.0.0.1");
        tomcat.getConnector();

        Path baseDir = Files.createTempDirectory("tomcat");
        tomcat.setBaseDir(baseDir.toString());
        Context context = tomcat.addContext("/app", baseDir.toString());

        Tomcat.addServlet(context, "DoFileWorker", new DoFileWorkerServlet());
        context.addServletMappingDecoded("/api/v1/*", "DoFileWorker");

        tomcat.start();
        System.out.println(tomcat.getHost().getName() + " Port: " + tomcat.getConnector().getLocalPort());

        tomcat.getServer().await();
    }
}