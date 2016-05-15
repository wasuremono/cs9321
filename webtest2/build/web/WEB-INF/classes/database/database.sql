/**
 * Author:  williamding
 * Created: May 11, 2016
 */

DROP TABLE booking;
DROP TABLE rooms;
DROP TABLE hotels;
DROP TABLE users;

CREATE TABLE users (
    id         integer NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    username   varchar(50) NOT NULL,
    password   varchar(50) NOT NULL,
    firstName  varchar(30),
    lastName   varchar(30),
    nickName   varchar(30),
    address    varchar(30),
    email      varchar(30),
    cardNumber varchar(16),
    cardName   varchar(30),
    cardExpireDate varchar(5),
    cvc        integer,
    userType   integer,
    PRIMARY KEY (id)
);

CREATE TABLE hotels (
    id      integer NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    preview varchar(200),
    city    varchar(50),
    owner   integer,
    PRIMARY KEY (id)
);

CREATE TABLE rooms(
    id        integer NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    price     varchar(30),
    hotel_id  integer,
    roomType  varchar(30),
    PRIMARY KEY (id)
);

CREATE TABLE booking(
    id       integer NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    checkin  date, 
    checkout date,
    cid varchar(30) NOT NULL,
    rid varchar(30) NOT NULL,
    PRIMARY KEY(id, cid, rid)
);

INSERT INTO users(username, password, firstname, lastname, nickname, email, userType) VALUES (
    'william', '12341234', 'william', 'ding', 'will', 'helloworld@1234.com',0
);