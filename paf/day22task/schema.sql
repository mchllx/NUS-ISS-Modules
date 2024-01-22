drop database if exists todo;

create database todo;

use todo;

-- task
create table task (
    -- taskId: number title: string dueDate: number priority: number completed: boolean
    task_id int auto_increment,
    title varchar(128) not null,
    due_date date,
    priority tinyint,
    completed boolean default false,

    constraint pk_task_id primary key (task_id)

);

-- task_summary
create table task_summary (
    -- taskId: number  title: string completed: boolean
    task_id int auto_increment,
    title varchar(128) not null,
    completed boolean,

    constraint pk_task_id primary key (task_id)

);

create table task_status (
    message varchar(256) not null,
    status boolean

);

grant all privileges on todo.* to root@'%';

flush privileges;

