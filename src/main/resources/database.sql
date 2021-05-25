-- auto-generated definition
create table users
(
    id         bigint       not null
        primary key auto_increment,
    email      varchar(255) null,
    first_name varchar(255) null,
    last_name  varchar(255) null,
    age        int,
    password   varchar(255) null
)
    engine = MyISAM;

-- auto-generated definition
create table roles
(
    id   bigint       not null
        primary key,
    name varchar(255) null
)
    engine = MyISAM;

-- auto-generated definition
create table users_roles
(
    User_id  bigint not null,
    roles_id bigint not null,
    primary key (User_id, roles_id)
)
    engine = MyISAM;

create index FKa62j07k5mhgifpp955h37ponj
    on users_roles (roles_id);

INSERT INTO users VALUES (1, 'admin', 'admin', 'admin', 100,'admin');
INSERT INTO users VALUES (2, 'user', 'user', 'user', 99, 'user');

INSERT INTO roles VALUES (1, 'admin');
INSERT INTO roles VALUES (2, 'user');

INSERT INTO users_roles VALUES (1, 1);
INSERT INTO users_roles VALUES (1, 2);
INSERT INTO users_roles VALUES (2, 2);

