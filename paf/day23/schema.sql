drop database if exists emart;

create database emart;

use emart;

create table purchase_order (

    Po_id varchar(8) not null,
    Created_on timestamp default current_timestamp,
    Name varchar(256) not null,
    Address varchar(256) not null,
    Last_update timestamp default timestamp on update current_timestamp

    primary key(Po_id)

);

-- create table line_item (
--     id int auto_increment,
--     item varchar(32) not null,
--     quantity int default '1',
--     po_id varchar(8) not null,

--     primary key (id)
--     constraint fk_po_id foreign key(po_id) references purchase_order(po_id)
-- );

-- grant all privileges on emart.* to mich@'%';

-- flush privileges;