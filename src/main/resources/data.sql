DROP TABLE IF EXISTS event CASCADE CONSTRAINTS;
DROP TABLE IF EXISTS credit CASCADE CONSTRAINTS;
DROP TABLE IF EXISTS message CASCADE CONSTRAINTS;
DROP TABLE IF EXISTS person CASCADE CONSTRAINTS;
DROP TABLE IF EXISTS profile CASCADE CONSTRAINTS;
DROP TABLE IF EXISTS MessageReply CASCADE CONSTRAINTS;

CREATE TABLE person (
  id bigint(20) NOT NULL PRIMARY KEY auto_increment,
  password varchar(255) DEFAULT NULL,
  role varchar(255) DEFAULT NULL,
  username varchar(255) DEFAULT NULL
);

CREATE TABLE profile (
  id bigint(20) NOT NULL PRIMARY KEY auto_increment,
  address varchar(255) DEFAULT NULL,
  city varchar(255) DEFAULT NULL,
  postal varchar(255) NOT NULL,
  country varchar(255) DEFAULT NULL,
  dob varchar(255) DEFAULT NULL,
  default_billing_address tinyint(1) DEFAULT NULL,
  default_shipping_address tinyint(1) DEFAULT NULL,
  register_profile tinyint(1) NOT NULL,
  email varchar(255) DEFAULT NULL,
  first_name varchar(255) DEFAULT NULL,
  last_name varchar(255) DEFAULT NULL,
  person_id bigint(20) DEFAULT NULL
);

CREATE TABLE credit (
  id bigint(20) NOT NULL PRIMARY KEY auto_increment,
  holder_name varchar(255) DEFAULT NULL,
  card_number varchar(255) DEFAULT NULL,
  card_type varchar(255) DEFAULT NULL,
  preferred bit(1) DEFAULT NULL,
  exp_date varchar(255) DEFAULT NULL,
  person_id bigint(20) DEFAULT NULL
);

CREATE TABLE message (
  id bigint(20) NOT NULL PRIMARY KEY auto_increment,
  message varchar(255) DEFAULT NULL,
  subject varchar(255) DEFAULT NULL,
  person_id bigint(20) DEFAULT NULL,
  msg_date bigint(20) NOT NULL,
  is_read tinyint(1) NOT NULL
);

CREATE TABLE MessageReply (
  id bigint(20) NOT NULL PRIMARY KEY auto_increment,
  message varchar(255) DEFAULT NULL,
  message_id bigint(20) DEFAULT NULL,
  person_id bigint(20) DEFAULT NULL,
  reply_date bigint(20) NOT NULL,
  is_read tinyint(1) NOT NULL
);

CREATE TABLE event (
  id bigint(20) NOT NULL PRIMARY KEY auto_increment,
  event_date bigint(20) DEFAULT NULL,
  event_type varchar(255) DEFAULT NULL,
  person_id bigint(20) DEFAULT NULL
);

alter table profile add constraint FKj4iunefvjq13m2j5a8l8odylw foreign key (person_id) references person (id);
alter table message add constraint FKrjg2ug55rdo338ks6514fw9qy foreign key (person_id) references person (id);
alter table MessageReply add constraint FKoc29jc9t0h5psnb4he5pmp7r3 foreign key (person_id) references person (id);
alter table credit add constraint FKnxm6f64dd9kv5aehddbq399n5 foreign key (person_id) references person (id);
--alter table activity add constraint FK6yqp6x5g64ntrfqqobkfopqfr foreign key (person_id) references person (id);

INSERT INTO person (id, password, role, username) VALUES (1, '$2a$10$PtdXXD5syMZXLRmtGXhJcOW/DxqaNbr/wMzQUY5Whqddeu4SS8bS6', 'ADMIN', 'jane');
INSERT INTO profile (id, address, city, postal, country, dob, default_billing_address, default_shipping_address, register_profile, email, first_name, last_name, person_id) VALUES
(1, 'Archer street', 'Toronto', '34144', 'Canada', '18/02/1995', NULL, NULL, 1, 'jane@gmail.com', 'Jane', 'Perry', 1);