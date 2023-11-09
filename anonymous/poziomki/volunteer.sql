create table volunteer
(
    volunteer_id   mediumint unsigned auto_increment
        primary key,
    username       varchar(255)                          not null,
    password       varchar(255)                          not null,
    color_category enum ('czerwony', 'żółty', 'zielony') not null,
    first_name     varchar(255)                          not null,
    last_name      varchar(255)                          not null,
    phone_number   varchar(15)                           not null,
    constraint username
        unique (username)
);

