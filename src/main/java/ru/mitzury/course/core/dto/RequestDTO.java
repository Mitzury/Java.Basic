package ru.mitzury.course.core.dto;

import java.util.List;

public class RequestDTO {

    private List<MessageDTO> msg;

    public List<MessageDTO> getMsg() {
        return msg;
    }

    public void setMsg(List<MessageDTO> msg) {
        this.msg = msg;
    }
}
