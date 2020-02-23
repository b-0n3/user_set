
create database user_set;

use user_set;
create table staff(
    id int , 
    birth_day varchar(10),
    birth_place varchar(30),
    sex         varchar(1),
    sit_fam     varchar(12),
    nationalite  varchar(23),
    date_embauche varchar(10),
    grade       varchar(21),
    functio_n   varchar(400),
    post        varchar(250),
    categorie   varchar(3),
    echelon     int,
    ent_effect  varchar(11),
    regime_retraite varchar(11),
    affil_recore int,
    date_der_promo varchar(11),
    image           blob,
    CONSTRAINT staff_pk PRIMARY KEY (id)
);

create table admin(
    username varchar(50),
    firstname varchar (50),
    lastname varchar (50),
    phonNumber varchar (14),
     birth_day Date,
    post varchar (50),
    password varchar(100),
    salt blob,
    image           blob,
    CONSTRAINT staff_pk PRIMARY KEY (username)
);