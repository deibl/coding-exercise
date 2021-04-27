--liquibase formatted sql
--changeset eibldavid:1 splitStatements:true endDelimiter:;

CREATE SEQUENCE hibernate_sequence
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

create table category
(
    id integer NOT NULL PRIMARY KEY,
    domain_id UUID NOT NULL,
    name varchar(255) NOT NULL,
    parent_id UUID
);

create table product
(
    id integer NOT NULL PRIMARY KEY,
    domain_id UUID NOT NULL,
    name varchar(255) NOT NULL,
    price float NOT NULL,
    category_id UUID NOT NULL
);

ALTER TABLE product ADD CONSTRAINT product_category_fk FOREIGN KEY (category_id) REFERENCES category(domain_id);

CREATE UNIQUE INDEX category_domain_id_index on category(domain_id);
CREATE UNIQUE INDEX cproduct_domain_id_index on product(domain_id);
