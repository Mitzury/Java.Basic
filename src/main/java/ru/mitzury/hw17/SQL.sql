CREATE DATABASE qna_db;

CREATE TABLE tests (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE questions (
    id SERIAL PRIMARY KEY,
    test_id INTEGER NOT NULL,
    question_text TEXT NOT NULL,

    CONSTRAINT fk_questions_test
        FOREIGN KEY (test_id)
        REFERENCES tests (id)
        ON DELETE CASCADE
);

CREATE TABLE answers (
    id SERIAL PRIMARY KEY,
    question_id INTEGER NOT NULL,
    answer_text TEXT NOT NULL,
    is_correct BOOLEAN NOT NULL DEFAULT FALSE,

    CONSTRAINT fk_answers_question
        FOREIGN KEY (question_id)
        REFERENCES questions (id)
        ON DELETE CASCADE
);

INSERT INTO tests (title, description)
VALUES ('Backend Development Test', 'Проверка знаний Java, PHP и OTUS');

INSERT INTO questions (test_id, question_text)
VALUES (1, 'Что такое JUnit Jupiter в экосистеме Java?');

INSERT INTO answers (question_id, answer_text, is_correct)
VALUES
    (1, 'Новый движок выполнения тестов JUnit 5', TRUE),
    (1, 'Система сборки Maven', FALSE),
    (1, 'Фреймворк для логирования', FALSE),
    (1, 'ORM-библиотека', FALSE);

INSERT INTO questions (test_id, question_text)
VALUES (1, 'Какой оператор используется в PHP для строгого сравнения?');

INSERT INTO answers (question_id, answer_text, is_correct)
VALUES
    (2, '==', FALSE),
    (2, '===', TRUE),
    (2, '=', FALSE),
    (2, '!=', FALSE);

INSERT INTO questions (test_id, question_text)
VALUES (1, 'Чем известна образовательная платформа OTUS?');

INSERT INTO answers (question_id, answer_text, is_correct)
VALUES
    (3, 'Онлайн-курсы для IT-специалистов', TRUE),
    (3, 'Хостинг-провайдер', FALSE),
    (3, 'Фреймворк для Java', FALSE);