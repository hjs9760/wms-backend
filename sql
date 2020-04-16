create table exercise (
       exercise_id bigint not null,
        member_id bigint not null,
        name varchar(255) not null,
        primary key (exercise_id)
    ) engine=InnoDB



create table hibernate_sequence (
       next_val bigint
    ) engine=InnoDB


create table member (
       member_id bigint not null auto_increment,
        access_token varchar(255),
        name varchar(255),
        oauth_id varchar(255),
        provider_name varchar(255),
        refresh_token varchar(255),
        primary key (member_id)
    ) engine=InnoDB


create table routine (
       routine_id bigint not null auto_increment,
        member_id bigint,
        name varchar(255),
        primary key (routine_id)
    ) engine=InnoDB



create table routine_exercise (
       routine_exercise_id bigint not null auto_increment,
        count integer,
        exercise_set integer,
        weight integer,
        exercise_id bigint,
        routine_id bigint,
        primary key (routine_exercise_id)
    ) engine=InnoDB