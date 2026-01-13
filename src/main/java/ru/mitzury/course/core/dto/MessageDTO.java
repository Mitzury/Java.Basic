package ru.mitzury.course.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class MessageDTO {

    @JsonProperty("MsgName")
    private String msgName;

    private JsonNode data;
    private JsonNode file;

    public String getMsgName() {
        return msgName;
    }

    public void setMsgName(String msgName) {
        this.msgName = msgName;
    }

    public JsonNode getData() {
        return data;
    }

    public void setData(JsonNode data) {
        this.data = data;
    }

    public JsonNode getFile() {
        return file;
    }

    public void setFile(JsonNode file) {
        this.file = file;
    }
}