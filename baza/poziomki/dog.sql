create table dog
(
    dog_id         mediumint unsigned auto_increment
        primary key,
    name           varchar(255)                          not null,
    color_category enum ('czerwony', 'żółty', 'zielony') not null,
    age            int                                   null,
    description    text                                  null
);

