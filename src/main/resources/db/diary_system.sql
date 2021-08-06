create database diarydb;
create user diary with encrypted password 'd!@ry';
grant all privileges on database diarydb to diary;


create table if not exists user_info (
    id serial primary key,
    username varchar(50) not null,
	name varchar(100) ,
    password varchar(255) not null,
    status smallint default 1,
	UNIQUE(username)
);

INSERT INTO user_info (USERNAME, PASSWORD, NAME, STATUS) VALUES 
('admin', '$2a$10$/yPpo36b4XBRvOyKu4lF.OJXbzLaFYQpcd/LWLnSPj0W3Q87iIKBC', 'Admin', 1),
('user', '$2a$10$/yPpo36b4XBRvOyKu4lF.OJXbzLaFYQpcd/LWLnSPj0W3Q87iIKBC',  'User', 2);

create table if not exists note_category (
    id serial primary key,
    name varchar(255) not null,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp,
    created_by smallint not null,
    updated_by smallint,
    CONSTRAINT fk_note_created_by
      FOREIGN KEY(created_by)
	  REFERENCES user_info(id)
);

create table if not exists note_info (
    id serial primary key,
    title varchar(255) not null,
    category_id smallint not null,
    content text,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp,
    created_by smallint not null,
    updated_by smallint,
    CONSTRAINT fk_note_category
      FOREIGN KEY(category_id)
	  REFERENCES note_category(id),
	CONSTRAINT fk_category_created_by
      FOREIGN KEY(created_by)
	  REFERENCES user_info(id)
);

