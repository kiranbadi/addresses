CREATE TABLE IF NOT EXISTS address_book.package_delivery_location
(
    location_id BIGSERIAL PRIMARY KEY,
    location_name VARCHAR(100),
    created_at    TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    deleted_at    TIMESTAMP WITH TIME ZONE DEFAULT NULL,
    created_by    VARCHAR(100),
    updated_by    VARCHAR(100)
);
grant all privileges on address_book.package_delivery_location to kiran;

insert into address_book.package_delivery_location (location_name, created_by, updated_by)
values ('front door', 'system', 'system');
insert into address_book.package_delivery_location (location_name, created_by, updated_by)
values ('back door', 'system', 'system');
insert into address_book.package_delivery_location (location_name, created_by, updated_by)
values ('side porch', 'system', 'system');
insert into address_book.package_delivery_location (location_name, created_by, updated_by)
values ('garage', 'system', 'system');
insert into address_book.package_delivery_location (location_name, created_by, updated_by)
values ('mail room', 'system', 'system');
insert into address_book.package_delivery_location (location_name, created_by, updated_by)
values ('porch', 'system', 'system');
insert into address_book.package_delivery_location (location_name, created_by, updated_by)
values ('property staff', 'system', 'system');
insert into address_book.package_delivery_location (location_name, created_by, updated_by)
values ('leasing office', 'system', 'system');
insert into address_book.package_delivery_location (location_name, created_by, updated_by)
values ('front desk', 'system', 'system');
insert into address_book.package_delivery_location (location_name, created_by, updated_by)
values ('concierge', 'system', 'system');
insert into address_book.package_delivery_location (location_name, created_by, updated_by)
values ('doorman', 'system', 'system');
insert into address_book.package_delivery_location (location_name, created_by, updated_by)
values ('security', 'system', 'system');
insert into address_book.package_delivery_location (location_name, created_by, updated_by)
values ('gate', 'system', 'system');
insert into address_book.package_delivery_location (location_name, created_by, updated_by)
values ('lobby', 'system', 'system');
insert into address_book.package_delivery_location (location_name, created_by, updated_by)
values ('other', 'system', 'system');
insert into address_book.package_delivery_location (location_name, created_by, updated_by)
values ('no preference', 'system', 'system');

create table address_book.package_delivery_location
(
    location_id   bigserial
        primary key,
    location_name varchar(100)
        constraint package_delivery_location_pk
            unique,
    created_at    timestamp with time zone default CURRENT_TIMESTAMP,
    updated_at    timestamp with time zone default CURRENT_TIMESTAMP,
    deleted_at    timestamp with time zone,
    created_by    varchar(100),
    updated_by    varchar(100)
);

alter table address_book.package_delivery_location
    owner to kiran;


