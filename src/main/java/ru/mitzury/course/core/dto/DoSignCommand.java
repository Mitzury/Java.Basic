package ru.mitzury.course.core.dto;

import java.time.ZonedDateTime;

public class DoSignCommand {

    private final ZonedDateTime  date;
    private final String file;

    public DoSignCommand(ZonedDateTime date, String file) {
        if (date == null) {
            throw new IllegalArgumentException("date must not be null");
        }
        if (file == null || file.isBlank()) {
            throw new IllegalArgumentException("file must not be empty");
        }
        this.date = date;
        this.file = file;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public String getFile() {
        return file;
    }
}
