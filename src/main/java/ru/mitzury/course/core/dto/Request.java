package ru.mitzury.course.core.dto;

public class Request {
    private Message msg;

    public Message getMSG() {
        return msg;
    }

    public void setMSG(Message MSG) {
        this.msg = MSG;
    }

    public void validate() {
        if (msg == null) {
            throw new IllegalArgumentException("MSG is required");
        }
        if (msg.name == null || msg.name.isBlank()) {
            throw new IllegalArgumentException("MSG.NAME is required");
        }
        if (msg.data == null) {
            throw new IllegalArgumentException("MSG.DATA is required");
        }
    }
}