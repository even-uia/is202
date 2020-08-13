drop table users;
drop table roles;

create table users (
    username varchar(32) not null primary key,
    password varchar(32) not null
);

create table roles (
    username varchar(32) not null,
    rolename varchar(32) not null,
    primary key (username,rolename)
);

insert into users values ('Even', 'hemmelig' );
insert into users values ('rolus', 'admin');
insert into users values ('roladm', 'admin');

insert into roles values ('rolus', 'RolodexUser');
insert into roles values ('roladm', 'RolodexAdmin');
