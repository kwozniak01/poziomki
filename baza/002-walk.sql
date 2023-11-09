create table walk
(
    walk_id      mediumint unsigned auto_increment
        primary key,
    dog_id       mediumint unsigned not null,
    volunteer_id mediumint unsigned null,
    walk_date    datetime           null,
    constraint walk_ibfk_1
        foreign key (dog_id) references dog (dog_id),
    constraint walk_ibfk_2
        foreign key (volunteer_id) references volunteer (volunteer_id)
);

create index dog_id
    on walk (dog_id);

create index volunteer_id
    on walk (volunteer_id);

