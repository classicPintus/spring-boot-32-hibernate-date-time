create table dummy_entity (
    local_time time(6) without time zone,
    id bigserial not null,
    instant timestamp(6) with time zone,
    local_date_time timestamp(6),
    zoned_date_time timestamp(6) with time zone,
    name varchar(255),
    primary key (id)
);