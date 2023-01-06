drop database if exists eshop;

create database eshop;

use eshop;

create table customers (
  name varchar(32) not null,
  address varchar(128) not null,
  email varchar(128) not null,

  primary key(name)
);

INSERT INTO customers (name,address,email) VALUES ("fred","201 Cobblestone Lane","fredflintstone@bedrock.com");
INSERT INTO customers (name,address,email) VALUES ("sherlock","221B Baker Street, London","sherlock@consultingdetective.org");
INSERT INTO customers (name,address,email) VALUES ("spongebob","124 Conch Street, Bikini Bottom","spongebob@yahoo.com");
INSERT INTO customers (name,address,email) VALUES ("jessica","698 Candlewood Land, Cabot Cove","fletcher@gmail.com");
INSERT INTO customers (name,address,email) VALUES ("dursley","4 Privet Drive, Little Whinging, Surrey","dursley@gmail.com");



create table estore (
    order_id char(8) not null,
    name varchar(32) not null,
    deliveryId char(8),
    orderDate date not null,

    primary key(order_id),
    constraint fk_customer_name
    foreign key(name) references 
    customers(name)
);

create table lineitems (

    item_id int auto_increment not null,
    item text not null,
    quantity int default 1,
    order_id char(8) not null,

    primary key(item_id),
    constraint fk_order_id
    foreign key(order_id) references estore(order_id)
);

create table order_status (
  delivery_id varchar(128) not null,
  order_id char(8) not null,
  status varchar(20),
  status_update date,

  primary key(delivery_id),
  constraint fk_ord_id
  foreign key(order_id) references estore(order_id)
);