package ru.mitzury.course.core.dto;

public class Response<T> {
    public boolean success;
    public String error;
    public T data;

    public static <T> Response<T> ok(T data) {
        Response<T> r = new Response<>();
        r.success = true;
        r.data = data;
        return r;
    }

    public static Response<?> error(String msg) {
        Response<?> r = new Response<>();
        r.success = false;
        r.error = msg;
        return r;
    }
}
