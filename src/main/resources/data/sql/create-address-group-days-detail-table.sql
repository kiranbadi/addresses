DROP TABLE IF EXISTS address_book.address_group_days_detail;
CREATE TABLE IF NOT EXISTS address_book.address_group_days_detail
(
    id                            bigserial     NOT NULL,
    group_days_id                 bigint        NOT NULL,
    week_days                     varchar(255)  NULL,
    is_open_24hours               boolean       NOT NULL,
    is_closed_for_delivery        boolean       NOT NULL,
    start_time                    varchar(255)   NULL,
    end_time                      varchar(255)   NULL,
    weekend_days                  varchar(255)  NULL,
    created_at                    timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at                    timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at                    timestamp     null,
    created_by                    varchar(255)  NOT NULL,
    updated_by                    varchar(255)  NOT NULL,
    CONSTRAINT address_group_days_detail_pkey PRIMARY KEY (id)
);
grant all privileges on address_book.address_group_days_detail to kiran;

create table address_book.address_group_days_detail
(
    id                     bigserial
        primary key,
    group_days_id          bigint                              not null
        constraint address_group_days_detail_address_group_days_group_days_id_fk
        references address_book.address_group_days (group_days_id),
    week_days              varchar(255),
    is_open_24hours        boolean                             not null,
    is_closed_for_delivery boolean                             not null,
    start_time             varchar(255),
    end_time               varchar(255),
    weekend_days           varchar(255),
    created_at             timestamp default CURRENT_TIMESTAMP not null,
    updated_at             timestamp default CURRENT_TIMESTAMP not null,
    deleted_at             timestamp,
    created_by             varchar(255)                        not null,
    updated_by             varchar(255)                        not null
);

alter table address_book.address_group_days_detail
    owner to kiran;

