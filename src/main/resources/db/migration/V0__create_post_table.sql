drop table if exists post_test;

create table post_test (
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

INSERT INTO post_test (member_id, category, title, content, file, image, hashtags, created_at, updated_at) VALUES (1, '개발', '페이징 처리 테스트하기', '안녕하세요!', 'www.file.com', 'www.image.com', '#hashtags', NOW(), NOW());