package ru.mitzury.course.core.dto;

import java.util.List;

public record RequestDTO(List<MessageDTO> msg) {
    public RequestDTO {
        if (msg == null || msg.isEmpty()) {
            throw new IllegalArgumentException("msg must not be empty");
        }
        msg = List.copyOf(msg);
    }

    public MessageDTO firstMessage() {
        return msg.get(0);
    }
}