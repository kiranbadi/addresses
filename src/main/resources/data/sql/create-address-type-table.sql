
DROP TABLE IF EXISTS address_book.address_type;
CREATE TABLE IF NOT EXISTS address_book.address_type
(
    id          serial PRIMARY KEY,
    name        varchar(255) NOT NULL,
    description varchar(255) NOT NULl,
    created_at  timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at  timestamp,
    created_by  varchar(255) NOT NULL,
    updated_by  varchar(255) NOT NULL,
    CONSTRAINT address_type_name_unique UNIQUE (name)
);
grant all privileges on address_book.address_type to kiran;

insert into address_book.address_type (name, description, created_by, updated_by)
values ('House', 'Residential House', 'system', 'system');
insert into address_book.address_type (name, description, created_by, updated_by)
values ('Commercial', 'Commercial Establishment', 'system', 'system');
insert into address_book.address_type (name, description, created_by, updated_by)
values ('Townhouse', 'Residential Townhouse', 'system', 'system');
insert into address_book.address_type (name, description, created_by, updated_by)
values ('Apartment', 'Residential Apartment', 'system', 'system');
insert into address_book.address_type (name, description, created_by, updated_by)
values ('Business', 'Business Establishment', 'system', 'system');
insert into address_book.address_type (name, description, created_by, updated_by)
values ('Other', 'Others', 'system', 'system');


create table address_book.address_type
(
    id          serial
        primary key,
    name        varchar(255)                        not null
        constraint address_type_name_unique
            unique,
    description varchar(255)                        not null,
    created_at  timestamp default CURRENT_TIMESTAMP not null,
    updated_at  timestamp default CURRENT_TIMESTAMP not null,
    deleted_at  timestamp,
    created_by  varchar(255)                        not null,
    updated_by  varchar(255)                        not null
);

alter table address_book.address_type
    owner to kiran;

