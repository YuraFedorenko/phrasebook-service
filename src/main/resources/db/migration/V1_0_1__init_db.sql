CREATE TABLE phrases
(
    id          bigint PRIMARY KEY,
    phrase      VARCHAR(50) UNIQUE NOT NULL,
    translation VARCHAR(50)        NOT NULL,
    lang_from   VARCHAR(3)         NOT NULL,
    lang_to     VARCHAR(3)         NOT NULL
);