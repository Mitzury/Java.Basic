package ru.mitzury.course.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public record MessageDTO(
        @JsonProperty("MsgName") String msgName,
        JsonNode payload
) {
    public MessageDTO {
        if (msgName == null || msgName.isBlank()) {
            throw new IllegalArgumentException("MsgName must not be empty");
        }
        if (payload == null) {
            throw new IllegalArgumentException("payload must not be null");
        }
    }
}