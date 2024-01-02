DROP TABLE IF EXISTS address_book.address_group_days;
CREATE TABLE IF NOT EXISTS address_book.address_group_days
(
    id                            bigserial     NOT NULL,
    address_id                    bigint UNIQUE NOT NULL,
    group_days_id                 bigint        NOT NULL,
    ungroup_week_days             boolean       NOT NULL DEFAULT false,
    ungroup_weekend_days          boolean       NOT NULL DEFAULT false,
    created_at                    timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at                    timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at                    timestamp     null,
    created_by                    varchar(255)  NOT NULL,
    updated_by                    varchar(255)  NOT NULL,
    CONSTRAINT address_group_days_pkey PRIMARY KEY (id)
);
grant all privileges on address_book.account_address_additional_detail to kiran;

create table address_book.address_group_days
(
    id                   bigserial
        primary key,
    address_id           bigint                              not null
        unique,
    group_days_id        bigint                              not null
        constraint address_group_days_pk
            unique,
    ungroup_week_days    boolean   default false             not null,
    ungroup_weekend_days boolean   default false             not null,
    created_at           timestamp default CURRENT_TIMESTAMP not null,
    updated_at           timestamp default CURRENT_TIMESTAMP not null,
    deleted_at           timestamp,
    created_by           varchar(255)                        not null,
    updated_by           varchar(255)                        not null
);

alter table address_book.address_group_days
    owner to kiran;

