drop database if exists shop;

create database shop;

use shop;

create table orders (
    order_id varchar(64) not null,
    order_date timestamp default current_timestamp,
    customer_name varchar(128) not null,
    ship_address varchar(128) not null,
    notes text,
    tax decimal(6,2) default 0.05,

    primary key(order_id)
);

create table order_detail (
    id int auto_increment,
    product varchar(64),
    unit_price decimal(6,2),
    discount decimal(6,2) default 1.0,
    quantity int,
    order_id varchar(64) not null,

    primary key(id),
    constraint fk_order_id foreign key(order_id) references orders(order_id)
);

grant all privileges on shop.* to mich@'%';

flush privileges;