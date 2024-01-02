
DROP TABLE IF EXISTS address_book.account_address_book;
CREATE TABLE IF NOT EXISTS address_book.account_address_book (
    id BIGSERIAL PRIMARY KEY,
    account_id  BIGINT NOT NULL,
    address_id  BIGINT NOT NULL,
    created_at    TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    deleted_at    TIMESTAMP WITH TIME ZONE DEFAULT NULL,
    created_by    VARCHAR(100),
    updated_by    VARCHAR(100),
    CONSTRAINT fk_account_address_book_account_id
        UNIQUE (account_id,address_id)

);
grant all privileges on address_book.package_delivery_location to kiran;

create table address_book.account_address_book
(
    id         bigserial
        primary key,
    account_id bigint not null,
    address_id bigint not null
        constraint account_address_book_account_address_detail_id_fk
            references address_book.account_address_detail,
    created_at timestamp with time zone default CURRENT_TIMESTAMP,
    updated_at timestamp with time zone default CURRENT_TIMESTAMP,
    deleted_at timestamp with time zone,
    created_by varchar(100),
    updated_by varchar(100),
    constraint fk_account_address_book_account_id
        unique (account_id, address_id)
);

alter table address_book.account_address_book
    owner to kiran;

