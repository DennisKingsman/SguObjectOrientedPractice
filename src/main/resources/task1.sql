create table credit_type(
	name varchar(100) not null primary key,
	conditions varchar not null,
	interest_rate integer not null,
	repayment_period_month integer not null
);


create table client(
	name varchar not null primary key,
	property_type varchar,
	address varchar,
	phone_number varchar not null,
	contact_person varchar not null
);


create table credit(
	credit_type_name varchar(100) references credit_type(name),
	client_name varchar references client(name),
	total_debt numeric not null,
	current_debt numeric not null,
	total_fine numeric,
	open_date date not null,
	close_date date
);