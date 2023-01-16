create table bookmark (
    id INT UNSIGNED not null auto_increment,
    member_id INT UNSIGNED not null,
    post_id INT UNSIGNED not null,
    created_at datetime(6),
    updated_at datetime(6),
    primary key (id)
);

create table share_info (
    id INT UNSIGNED not null auto_increment,
    content varchar(255),
    hashtag varchar(255),
    image varchar(255),
    link varchar(255),
    title varchar(255),
    created_at datetime(6),
    updated_at datetime(6),
    primary key (id)
);

create table post (
    id INT UNSIGNED not null auto_increment,
    created_at datetime(6),
    updated_at datetime(6),
    category varchar(255) not null,
    content varchar(255) not null,
    file varchar(255),
    hashtags varchar(255),
    image varchar(255),
    is_anonymous bit default false not null,
    like_numbers integer default 0 not null,
    member_id bigint not null,
    title varchar(255) not null,
    views integer default 0 not null,
    primary key (id)
);