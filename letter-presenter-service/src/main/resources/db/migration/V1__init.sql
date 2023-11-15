SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET default_tablespace = '';

CREATE TABLE IF NOT EXISTS letters
(
    id    bigint NOT NULL,
    letter  varchar(3) NOT NULL,
    tracing_id varchar(255) NOT NULL,
    created_at timestamp with time zone NOT NULL,
    constraint letters_pk primary key (id)
);

CREATE SEQUENCE IF NOT EXISTS letters_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
