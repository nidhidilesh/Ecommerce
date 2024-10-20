create table products (
	id int primary key not null unique,
	name varchar(30) not null,
	price varchar(10) not null,
	category varchar(30) not null,
	in_stock boolean,
	timestamp DATE
);