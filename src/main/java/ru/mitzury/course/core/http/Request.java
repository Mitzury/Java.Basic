package ru.mitzury.course.core.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public class Request {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private final HttpServletRequest raw;

    public Request(HttpServletRequest raw) {
        this.raw = raw;
    }

    public String method() {
        return raw.getMethod();
    }

    public <T> T json(Class<T> type) throws IOException {
        return MAPPER.readValue(raw.getInputStream(), type);
    }

    public String path() {
        return raw.getPathInfo();
    }

    public String header(String name) {
        return raw.getHeader(name);
    }

    public Map<String, String[]> queryParams() {
        return raw.getParameterMap();
    }

    public String body() throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = raw.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        return sb.toString();
    }
}
