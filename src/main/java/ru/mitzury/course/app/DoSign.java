package ru.mitzury.course.app;

import ru.mitzury.course.core.BadRequestException;

public class DoSign {

    public String sign(String data) {

        if (data == null || data.isBlank()) {
            throw new BadRequestException("Field 'data' is required for DoSign");
        }

        // здесь реальная бизнес-логика подписи
        return "signed:" + data;
    }
}