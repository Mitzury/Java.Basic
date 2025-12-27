package ru.mitzury.course.core;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ViewRenderer {

    public static String render(String viewName) throws IOException {
        String path = "/html/" + viewName + ".html";

        try (InputStream is = ViewRenderer.class.getResourceAsStream(path)) {
            if (is == null) {
                throw new IOException("View not found: " + viewName);
            }
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}