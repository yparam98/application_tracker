create table address (
    id integer auto_increment not null,
    street_num integer not null,
    street_name varchar(30) not null,
    city varchar(20) not null,
    state varchar(20) not null,
    country varchar(20) not null,
    primary key (id)
);

create table contact (
    id integer auto_increment not null,
    email varchar(50) not null,
    phone varchar(11) not null,
    address_id integer not null,
    primary key (id),
    foreign key address_id references address(id)
);

create table candidate (
    id integer auto_increment,
    firstname varchar(20) not null,
    lastname varchar(20) not null,
    contact_id integer not null,
    primary key (id),
    foreign key contact_id references contact(id)
);

create table company (
    id integer auto_increment not null,
    name varchar(30) auto increment not null,
    contact_id integer not null,
    hr_name varchar(50),
    primary key (id),
    foreign key contact_id references contact(id)
);

create table application (
    id integer auto_increment,
    description varchar(50) not null,
    resume varchar(50),
    cover_letter varchar(50) not null,
    company_id integer not null,
    candidate_id integer not null,
    url varchar(256) not null,
    status varchar(10) not null,
    primary key (id),
    foreign key candidate_id references candidate(id),
    foreign key company_id references company(id)
);