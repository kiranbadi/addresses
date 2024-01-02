DROP TABLE IF EXISTS address_book.account_address_additional_detail;
CREATE TABLE IF NOT EXISTS address_book.account_address_additional_detail
(
    id                            bigserial     NOT NULL,
    address_id                    bigint UNIQUE NOT NULL,
    package_delivery_location     varchar(255),
    package_delivery_instructions varchar(255),
    group_days_id                 bigint  null,
    security_code                 varchar(255)  null,
    key_fob                       varchar(255)  null,
    gate_code                     varchar(255)  null,
    call_box                      varchar(255)  null,
    additional_instruction        varchar(255)  null,
    nearest_landmark              varchar(255)  null,
    created_at                    timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at                    timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at                    timestamp     null,
    created_by                    varchar(255)  NOT NULL,
    updated_by                    varchar(255)  NOT NULL,
    CONSTRAINT fk_account_address_additional_detail_address_id FOREIGN KEY (address_id)
        REFERENCES address_book.account_address_detail (id),
    CONSTRAINT account_address_additional_detail_uk UNIQUE (group_days_id, address_id)
);
grant all privileges on address_book.account_address_additional_detail to kiran;


/*
  TABLE DDL FROM DOCKER
 */

create table if not exists address_book.account_address_additional_detail
(
    id                            bigserial,
    address_id                    bigint                              not null
        unique
        constraint fk_account_address_additional_detail_address_id
            references address_book.account_address_detail,
    package_delivery_location     varchar(255)
        constraint account_address_additional_detail_package_delivery_location_loc
            references address_book.package_delivery_location (location_name),
    package_delivery_instructions varchar(255),
    group_days_id                 bigint
        constraint account_address_additional_detail_address_group_days_group_days
            references address_book.address_group_days (group_days_id),
    security_code                 varchar(255),
    key_fob                       varchar(255),
    gate_code                     varchar(255),
    call_box                      varchar(255),
    additional_instruction        varchar(255),
    nearest_landmark              varchar(255),
    created_at                    timestamp default CURRENT_TIMESTAMP not null,
    updated_at                    timestamp default CURRENT_TIMESTAMP not null,
    deleted_at                    timestamp,
    created_by                    varchar(255)                        not null,
    updated_by                    varchar(255)                        not null,
    constraint account_address_additional_detail_uk
        unique (group_days_id, address_id)
);

alter table address_book.account_address_additional_detail
    owner to kiran;

