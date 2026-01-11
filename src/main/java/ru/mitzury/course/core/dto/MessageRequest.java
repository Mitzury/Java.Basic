package ru.mitzury.course.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MessageRequest {

    @JsonProperty("msg")
    public List<Message> messages;

    public static class Message {
        @JsonProperty("MsgName")
        public String msgName;

        public String file;
        public String data;
    }
}
