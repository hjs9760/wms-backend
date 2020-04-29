create table exercise (
       exercise_id bigint not null auto_increment,
        category varchar(255),
        member_id bigint,
        name varchar(255),
        primary key (exercise_id)
    ) engine=InnoDB

 create table history (
       history_id bigint not null auto_increment,
        count integer,
        edate datetime,
        exercise_set integer,
        member_id bigint,
        sdate datetime,
        weight double precision,
        exercise_id bigint,
        primary key (history_id)
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
         weight double precision,
         exercise_id bigint,
         routine_id bigint,
         primary key (routine_exercise_id)
     ) engine=InnoDB

