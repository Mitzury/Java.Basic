package ru.mitzury.course.core.dto;

public class DoSignCommand {

    private final String data;
    private final String file;

    public DoSignCommand(String data, String file) {
        if (data == null || data.isBlank()) {
            throw new IllegalArgumentException("data must not be empty");
        }
        if (file == null || file.isBlank()) {
            throw new IllegalArgumentException("file must not be empty");
        }
        this.data = data;
        this.file = file;
    }

    public String getData() {
        return data;
    }

    public String getFile() {
        return file;
    }
}
