drop table if exists post;

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