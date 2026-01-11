package ru.mitzury.course.core.dto;

public class Response {

    public String status;
    public String error;

    public static Response success() {
        Response r = new Response();
        r.status = "success";
        return r;
    }

    public static Response error(String msg) {
        Response r = new Response();
        r.status = "error";
        r.error = msg;
        return r;
    }
}