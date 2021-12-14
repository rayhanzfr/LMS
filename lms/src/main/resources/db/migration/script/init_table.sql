CREATE TABLE roles(
	id serial,
	role_code varchar(10),
	names varchar(10),
	created_by integer,
	created_date timestamp without time zone,
	isactive boolean,
	update_by integer,
	update_date timestamp without time zone,
	version integer
);
ALTER TABLE roles
	ADD CONSTRAINT role_pk PRIMARY KEY (id);
ALTER TABLE roles
	ADD CONSTRAINT role_bk UNIQUE(role_code);
ALTER TABLE roles
	ALTER COLUMN role_code SET NOT NULL;
ALTER TABLE roles
	ALTER COLUMN created_by SET NOT NULL;
ALTER TABLE roles
	ALTER COLUMN created_date SET NOT NULL;
ALTER TABLE roles
	ALTER COLUMN isactive SET NOT NULL;
	
CREATE TABLE users(
	id serial,
	usernames varchar(16),
	pass text,
	email varchar(20),
	id_role integer,
	created_by integer,
	created_date timestamp without time zone,
	isactive boolean,
	update_by integer,
	update_date timestamp without time zone,
	version integer
);
ALTER TABLE users
	ADD CONSTRAINT user_pk PRIMARY KEY (id);
ALTER TABLE users
	ADD CONSTRAINT user_bk UNIQUE (usernames);
ALTER TABLE users
	ADD CONSTRAINT role_fk FOREIGN KEY (id_role)
	REFERENCES roles(id);
ALTER TABLE users
	ALTER COLUMN id_role SET NOT NULL;
ALTER TABLE users
	ALTER COLUMN usernames SET NOT NULL;
ALTER TABLE users
	ALTER COLUMN pass SET NOT NULL;
ALTER TABLE users
	ALTER COLUMN created_by SET NOT NULL;
ALTER TABLE users
	ALTER COLUMN created_date SET NOT NULL;
ALTER TABLE users
	ALTER COLUMN isactive SET NOT NULL;

CREATE TABLE cities (
	id serial,
	city_code varchar(10),
	names varchar(30),
	created_by integer,
	created_date timestamp without time zone,
	isactive boolean,
	update_by integer,
	update_date timestamp without time zone,
	version integer
);
ALTER TABLE cities
	ADD CONSTRAINT city_pk PRIMARY KEY (id);
ALTER TABLE cities
	ADD CONSTRAINT city_bk UNIQUE (city_code);
ALTER TABLE cities
	ALTER COLUMN city_code SET NOT NULL;
ALTER TABLE cities
	ALTER COLUMN names SET NOT NULL;
ALTER TABLE cities
	ALTER COLUMN created_by SET NOT NULL;
ALTER TABLE cities
	ALTER COLUMN created_date SET NOT NULL;
ALTER TABLE cities
	ALTER COLUMN isactive SET NOT NULL;
	
CREATE TABLE branches (
	id serial,
	id_city integer,
	branches_code varchar(30),
	branches_name varchar(50),
	created_by integer,
	created_date timestamp without time zone,
	isactive boolean,
	update_by integer,
	update_date timestamp without time zone,
	version integer
);
ALTER TABLE branches
	ADD CONSTRAINT branch_pk PRIMARY KEY (id);
ALTER TABLE branches
	ADD CONSTRAINT city_fk FOREIGN KEY(id_city)
	REFERENCES cities(id);
ALTER TABLE branches
	ADD CONSTRAINT branch_bk UNIQUE (branches_code);
ALTER TABLE branches
	ALTER COLUMN branches_code SET NOT NULL;
ALTER TABLE branches
	ALTER COLUMN branches_name SET NOT NULL;
ALTER TABLE branches
	ALTER COLUMN created_by SET NOT NULL;
ALTER TABLE branches
	ALTER COLUMN created_date SET NOT NULL;
ALTER TABLE branches
	ALTER COLUMN isactive SET NOT NULL;
	
CREATE TABLE profile (
	id serial,
	id_branch integer,
	id_user integer,
	profile_code varchar(30),
	firstnames varchar(30),
	lastnames varchar(30),
	phonenumber varchar(13),
	created_by integer,
	created_date timestamp without time zone,
	isactive boolean,
	update_by integer,
	update_date timestamp without time zone,
	version integer
);
ALTER TABLE profile
	ADD CONSTRAINT profile_pk PRIMARY KEY(id);
ALTER TABLE profile
	ADD CONSTRAINT branches_fk FOREIGN KEY(id_branch)
	REFERENCES branches(id);
ALTER TABLE profile
	ADD CONSTRAINT users_fk FOREIGN KEY(id_user)
	REFERENCES users(id);
ALTER TABLE profile
	ADD CONSTRAINT profile_bk UNIQUE(profile_code);
ALTER TABLE profile
	ALTER COLUMN profile_code SET NOT NULL;
ALTER TABLE profile
	ALTER COLUMN id_branch SET NOT NULL;
ALTER TABLE profile
	ALTER COLUMN id_user SET NOT NULL;
ALTER TABLE profile
	ALTER COLUMN firstnames SET NOT NULL;
ALTER TABLE profile
	ALTER COLUMN created_by SET NOT NULL;
ALTER TABLE profile
	ALTER COLUMN created_date SET NOT NULL;
ALTER TABLE profile
	ALTER COLUMN isactive SET NOT NULL;


CREATE TABLE service_type (
	id serial,
	service_code varchar(20),
	service_name varchar(30),
	created_by integer,
	created_date timestamp without time zone,
	isactive boolean,
	update_by integer,
	update_date timestamp without time zone,
	version integer
);
ALTER TABLE service_type
	ADD CONSTRAINT service_pk PRIMARY KEY(id);
ALTER TABLE service_type 
	ADD CONSTRAINT service_bk UNIQUE(service_code);
ALTER TABLE service_type
	ALTER COLUMN service_code SET NOT NULL;
ALTER TABLE service_type
	ALTER COLUMN created_by SET NOT NULL;
ALTER TABLE service_type
	ALTER COLUMN created_date SET NOT NULL;
ALTER TABLE service_type
	ALTER COLUMN isactive SET NOT NULL;

CREATE TABLE payment_method (
	id serial,
	payment_code varchar(30),
	payment_name varchar(30),
	created_by integer,
	created_date timestamp without time zone,
	isactive boolean,
	update_by integer,
	update_date timestamp without time zone,
	version integer
);
ALTER TABLE payment_method
	ADD CONSTRAINT payment_pk PRIMARY KEY(id);
ALTER TABLE payment_method 
	ADD CONSTRAINT payment_bk UNIQUE(payment_code);
ALTER TABLE payment_method
	ALTER COLUMN payment_code SET NOT NULL;
ALTER TABLE payment_method
	ALTER COLUMN created_by SET NOT NULL;
ALTER TABLE payment_method
	ALTER COLUMN created_date SET NOT NULL;
ALTER TABLE payment_method
	ALTER COLUMN isactive SET NOT NULL;

CREATE TABLE default_price (
	id serial,
	id_branches integer,
	id_service_type integer,
	default_price_code varchar(20),
	price numeric,
	created_by integer,
	created_date timestamp without time zone,
	isactive boolean,
	update_by integer,
	update_date timestamp without time zone,
	version integer
);
ALTER TABLE default_price
	ADD CONSTRAINT default_price_pk PRIMARY KEY(id);
ALTER TABLE default_price
	ADD CONSTRAINT branches_fk FOREIGN KEY(id_branches)
	REFERENCES branches(id);
ALTER TABLE default_price
	ADD CONSTRAINT service_type_fk FOREIGN KEY(id_service_type)
	REFERENCES service_type(id);
ALTER TABLE default_price 
	ADD CONSTRAINT default_price_bk UNIQUE(default_price_code);
ALTER TABLE default_price
	ALTER COLUMN default_price_code SET NOT NULL;
ALTER TABLE default_price
	ALTER COLUMN created_by SET NOT NULL;
ALTER TABLE default_price
	ALTER COLUMN created_date SET NOT NULL;
ALTER TABLE default_price
	ALTER COLUMN isactive SET NOT NULL;

CREATE TABLE status (
	id serial,
	status_code varchar(30),
	status_name varchar(30),
	created_by integer,
	created_date timestamp without time zone,
	isactive boolean,
	update_by integer,
	update_date timestamp without time zone,
	version integer
);
ALTER TABLE status
	ADD CONSTRAINT status_pk PRIMARY KEY(id);
ALTER TABLE status 
	ADD CONSTRAINT status_bk UNIQUE(status_code);
ALTER TABLE status
	ALTER COLUMN status_code SET NOT NULL;
ALTER TABLE status
	ALTER COLUMN created_by SET NOT NULL;
ALTER TABLE status
	ALTER COLUMN created_date SET NOT NULL;
ALTER TABLE status
	ALTER COLUMN isactive SET NOT NULL;


CREATE TABLE categories (
	id serial,
	cat_code varchar(10),
	names varchar(30),
	created_by integer,
	created_date timestamp without time zone,
	isactive boolean,
	update_by integer,
	update_date timestamp without time zone,
	version integer
);
ALTER TABLE categories
	ADD CONSTRAINT cat_pk PRIMARY KEY (id);
ALTER TABLE categories
	ADD CONSTRAINT cat_bk UNIQUE (cat_code);
ALTER TABLE categories
	ALTER COLUMN cat_code SET NOT NULL;
ALTER TABLE status
	ALTER COLUMN created_by SET NOT NULL;
ALTER TABLE status
	ALTER COLUMN created_date SET NOT NULL;
ALTER TABLE status
	ALTER COLUMN isactive SET NOT NULL;

CREATE TABLE shipment (
	id serial,
	id_status integer,
	id_payment integer,
	id_service_type integer,
	receiver_destination integer,
	shipment_number varchar(10),
	sender_name varchar(30),
	receiver_name varchar(30),
	phone_receiver varchar(13),
	address text,
	shipping_date timestamp without time zone,
	price numeric,
	created_by integer ,
	created_date timestamp without time zone,
	isactive boolean,
	update_by integer,
	update_date timestamp without time zone,
	version integer
);
ALTER TABLE shipment 
	ADD CONSTRAINT shipment_pk PRIMARY KEY(id);
ALTER TABLE shipment 
	ADD CONSTRAINT shipment_bk UNIQUE(shipment_number);
ALTER TABLE shipment 
	ADD CONSTRAINT branches_fk FOREIGN KEY (receiver_destination)
	REFERENCES branches(id);
ALTER TABLE shipment 
	ADD CONSTRAINT payment_fk FOREIGN KEY (id_payment)
	REFERENCES payment_method(id);
ALTER TABLE shipment 
	ADD CONSTRAINT service_type_fk FOREIGN KEY (id_service_type) 
	REFERENCES service_type(id);
ALTER TABLE shipment 
	ADD CONSTRAINT status_fk FOREIGN KEY (id_status)
	REFERENCES status(id);
ALTER TABLE shipment
	ALTER COLUMN shipment_number SET NOT NULL;
ALTER TABLE shipment
	ALTER COLUMN id_payment SET NOT NULL;
ALTER TABLE shipment
	ALTER COLUMN receiver_destination SET NOT NULL;
ALTER TABLE shipment
	ALTER COLUMN id_service_type SET NOT NULL;
ALTER TABLE shipment
	ALTER COLUMN id_status SET NOT NULL;
ALTER TABLE shipment
	ALTER COLUMN created_by SET NOT NULL;
ALTER TABLE shipment
	ALTER COLUMN created_date SET NOT NULL;
ALTER TABLE shipment
	ALTER COLUMN isactive SET NOT NULL;

CREATE TABLE shipment_delivery (
	id serial,
	id_shipment integer,
	receiver_name varchar(30),
	id_status integer,
	time_delivered timestamp without time zone,
	created_by integer,
	created_date timestamp without time zone,
	isactive boolean,
	update_by integer,
	update_date timestamp without time zone,
	version integer
);
ALTER TABLE shipment_delivery 
	ADD CONSTRAINT ship_deliv_pk PRIMARY KEY(id);
ALTER TABLE shipment_delivery
	ADD CONSTRAINT shipment_fk FOREIGN KEY (id_shipment) 
	REFERENCES shipment(id);
ALTER TABLE shipment_delivery 
	ADD CONSTRAINT status_fk FOREIGN KEY (id_status) 
	REFERENCES status(id);
ALTER TABLE shipment_delivery
	ALTER COLUMN id_shipment SET NOT NULL;
ALTER TABLE shipment_delivery
	ALTER COLUMN id_status SET NOT NULL;
ALTER TABLE shipment_delivery
	ALTER COLUMN created_by SET NOT NULL;
ALTER TABLE shipment_delivery
	ALTER COLUMN created_date SET NOT NULL;
ALTER TABLE shipment_delivery
	ALTER COLUMN isactive SET NOT NULL;

CREATE TABLE shipment_detail (
	id serial,
	id_shipment integer,
	id_cat integer,
	item_name varchar(30),
	weight integer,
	quantity integer,
	created_by integer,
	created_date timestamp without time zone,
	isactive boolean,
	update_by integer,
	update_date timestamp without time zone,
	version integer
);
ALTER TABLE shipment_detail 
	ADD CONSTRAINT ship_detail_pk PRIMARY KEY (id);
ALTER TABLE shipment_detail 
	ADD CONSTRAINT cat_fk FOREIGN KEY (id_cat) 
	REFERENCES categories(id);
ALTER TABLE shipment_detail 
	ADD CONSTRAINT shipment_fk FOREIGN KEY (id_shipment) 
	REFERENCES shipment(id);
ALTER TABLE shipment_detail
	ALTER COLUMN id_cat SET NOT NULL;
ALTER TABLE shipment_detail
	ALTER COLUMN id_shipment SET NOT NULL;
ALTER TABLE shipment_detail
	ALTER COLUMN created_by SET NOT NULL;
ALTER TABLE shipment_detail
	ALTER COLUMN created_date SET NOT NULL;
ALTER TABLE shipment_detail
	ALTER COLUMN isactive SET NOT NULL;