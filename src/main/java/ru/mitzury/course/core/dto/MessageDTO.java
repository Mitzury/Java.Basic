package ru.mitzury.course.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class MessageDTO {

    @JsonProperty("MsgName")
    private String msgName;

    @JsonProperty("payload")
    private JsonNode payload;

    public String getMsgName() {
        return msgName;
    }

    public JsonNode getPayload() {
        return payload;
    }
}
