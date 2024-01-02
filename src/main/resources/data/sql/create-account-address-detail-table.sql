
DROP TABLE IF EXISTS address_book.account_address_detail;
CREATE TABLE IF NOT EXISTS address_book.account_address_detail
(
    id           BIGSERIAL PRIMARY KEY,
    account_id   BIGINT  NOT NULL,
    country_code VARCHAR(2)  NOT NULL,
    state_code   VARCHAR(2)  NOT NULL,
    city         VARCHAR(50) NOT NULL,
    zip_code     VARCHAR(10) NOT NULL,
    full_name    VARCHAR(50) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    house_number VARCHAR(10) NOT NULL,
    street       VARCHAR(50) NOT NULL,
    is_primary   BOOLEAN NOT NULL DEFAULT FALSE,
    address_type VARCHAR(20) NOT NULL,
    created_at   TIMESTAMP   NOT NULL DEFAULT NOW(),
    updated_at   TIMESTAMP   NOT NULL DEFAULT NOW(),
    created_by   VARCHAR(50) NOT NULL,
    updated_by   VARCHAR(50) NOT NULL,
    deleted_at   TIMESTAMP,
    CONSTRAINT fk_account_address_detail_address_type
        FOREIGN KEY (address_type) REFERENCES address_book.address_type (name)
);
grant all privileges on address_book.account_address_detail to kiran;

create table address_book.account_address_detail
(
    id           bigserial
        primary key,
    account_id   bigint                  not null,
    country_code varchar(2)              not null,
    state_code   varchar(2)              not null,
    city         varchar(50)             not null,
    zip_code     varchar(10)             not null,
    full_name    varchar(50)             not null,
    phone_number varchar(20)             not null,
    house_number varchar(10)             not null,
    street       varchar(50)             not null,
    is_primary   boolean   default false not null,
    address_type varchar(20)             not null
        constraint fk_account_address_detail_address_type
        references address_book.address_type (name),
    created_at   timestamp default now() not null,
    updated_at   timestamp default now() not null,
    created_by   varchar(50)             not null,
    updated_by   varchar(50)             not null,
    deleted_at   timestamp
);

alter table address_book.account_address_detail
    owner to kiran;

