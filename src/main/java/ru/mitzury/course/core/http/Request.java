package ru.mitzury.course.core.http;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class Request {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final HttpServletRequest raw;
    private JsonNode cachedJson; // читаем body ОДИН раз

    public Request(HttpServletRequest raw) {
        this.raw = raw;
    }

    public String method() {
        return raw.getMethod();
    }

    public JsonNode jsonTree() throws IOException {
        if (cachedJson == null) {
            cachedJson = MAPPER.readTree(raw.getInputStream());
        }
        return cachedJson;
    }

    public <T> T json(Class<T> type) throws IOException {
        return MAPPER.treeToValue(jsonTree(), type);
    }
}
