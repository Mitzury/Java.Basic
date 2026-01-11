package ru.mitzury.course;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import ru.mitzury.course.core.DispatcherServlet;

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
        Context context = tomcat.addContext("", baseDir.toString());

        Tomcat.addServlet(context, "dispatcher", new DispatcherServlet());
        context.addServletMappingDecoded("/api/v1/*", "dispatcher");

        tomcat.start();
        System.out.println(tomcat.getHost() + " Port: " + tomcat.getConnector().getLocalPort());

        tomcat.getServer().await();
    }
}