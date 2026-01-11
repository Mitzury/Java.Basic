package ru.mitzury.course.core;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}