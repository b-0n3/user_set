drop database user_set;
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
    date_dern_promo varchar(11),
    image           blob,
    CONSTRAINT staff_pk PRIMARY KEY (id)
);

create table admin(
    password varchar(100),
    salt blob,
    username varchar(12)
);