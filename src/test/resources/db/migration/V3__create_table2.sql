create table comment (
    id INT UNSIGNED not null auto_increment,
    created_at datetime(6),
    updated_at datetime(6),
    comment_id bigint not null,
    content varchar(255),
    is_anonymous bit not null,
    layer integer,
    member_id bigint not null,
    post_id bigint not null,
    primary key (id)
);

create table member (
    id INT UNSIGNED not null auto_increment,
    created_at datetime(6),
    updated_at datetime(6),
    email varchar(255) not null,
    role boolean default true,
    is_active boolean default true,
    name varchar(255) not null,
    password varchar(255) not null,
    primary key (id)
);