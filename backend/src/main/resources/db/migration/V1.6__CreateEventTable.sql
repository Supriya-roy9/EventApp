CREATE TABLE EVENT (
    ID BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL,
    LOCATION VARCHAR(255) NOT NULL,
    EVENT_DATE DATE NOT NULL,
    CAPACITY INT NOT NULL,
    AVAILABILITY INT NOT NULL,
    PRICE DOUBLE PRECISION NOT NULL
);