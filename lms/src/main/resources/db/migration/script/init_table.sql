CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE roles (
   id varchar(36)  PRIMARY KEY DEFAULT  uuid_generate_v4(),
   roles_code varchar(15) UNIQUE NOT NULL,
   roles_name varchar(30) NOT NULL,
   "version" integer NOT NULL,
   created_by text NOT NULL,
   created_at timestamp without time zone NOT NULL,
   updated_by text,
   updated_at timestamp without time zone,
   is_active boolean NOT NULL
);


CREATE TABLE users (
	id varchar(36) DEFAULT  uuid_generate_v4(),
	roles_id varchar(36) NOT NULL,
	users_email varchar(50) UNIQUE NOT null,
	users_password TEXT NOT null,
	"version" integer NOT NULL,
	created_by text NOT NULL,
	created_at timestamp WITHOUT time ZONE NOT null,
	updated_by text,
	updated_at timestamp WITHOUT time ZONE,
	is_active boolean NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY (roles_id)
	REFERENCES roles(id)
);


CREATE TABLE permissions (
   id varchar(36) PRIMARY KEY DEFAULT  uuid_generate_v4(),
   permissions_code varchar(15) UNIQUE NOT NULL,
   permissions_name varchar(30) NOT NULL,
   "version" integer NOT NULL,
   created_by text NOT NULL,
   created_at timestamp without time zone NOT NULL,
   updated_by text,
   updated_at timestamp without time zone,
   is_active boolean NOT NULL
);


CREATE TABLE permissions_roles (
   id varchar(36) PRIMARY KEY DEFAULT  uuid_generate_v4(),
   permissions_id varchar(36) NOT NULL,
   roles_id varchar(36) NOT NULL,
   "version" integer NOT NULL,
   created_by text NOT NULL,
   created_at timestamp without time zone NOT NULL,
   updated_by text,
   updated_at timestamp without time zone,
   is_active boolean NOT NULL,
   FOREIGN KEY (roles_id)
      REFERENCES roles (id),
   FOREIGN KEY (permissions_id)
      REFERENCES permissions (id)
);



CREATE TABLE employees(
	id varchar(36) DEFAULT  uuid_generate_v4(),
	users_id varchar(36) NOT null,
	companies_id varchar(36) NOT null,
	employees_code varchar(15)  unique not null,
	employees_fullname TEXT NOT NULL,
	employees_address TEXT,
	employees_phone_number varchar(14) unique NOT NULL,
	"version" integer NOT null,
	created_by text NOT null,
	created_at timestamp WITHOUT time ZONE NOT null,
	updated_by text,
	updated_at timestamp WITHOUT time ZONE,
	is_active boolean NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (users_id)
		REFERENCES users(id)
);


CREATE TABLE items_types(
	id varchar(36) PRIMARY KEY DEFAULT  uuid_generate_v4(),
	items_types_code varchar(15) UNIQUE NOT NULL,
	items_types_name varchar(50) UNIQUE NOT NULL,
	"version" integer NOT NULL,
	created_by text NOT NULL,
	created_at timestamp WITHOUT time ZONE NOT null,
	updated_by text,
	updated_at timestamp WITHOUT time ZONE,
	is_active boolean NOT NULL
);

CREATE TABLE files(
	id varchar(36) PRIMARY KEY DEFAULT  uuid_generate_v4(),
	file bytea NOT NULL,
	extensions varchar(5) NOT NULL,
	"version" integer NOT NULL,
	created_by text NOT NULL,
	created_at timestamp WITHOUT time ZONE NOT null,
	updated_by text,
	updated_at timestamp WITHOUT time ZONE,
	is_active boolean NOT NULL
);

CREATE TABLE items_brands(
	id varchar(36) PRIMARY KEY DEFAULT  uuid_generate_v4(),
	items_brands_code varchar(15) UNIQUE NOT NULL,
	items_brands_name varchar(50) UNIQUE NOT NULL,
	"version" integer NOT NULL,
	created_by text NOT NULL,
	created_at timestamp WITHOUT time ZONE NOT null,
	updated_by text,
	updated_at timestamp WITHOUT time ZONE,
	is_active boolean NOT NULL
);

CREATE TABLE items (
   id varchar(36) PRIMARY KEY DEFAULT  uuid_generate_v4(),
   files_id varchar(36) NOT NULL,
   items_types_id varchar(36) NOT NULL,
   items_brands_id varchar(36) NOT NULL,
   items_code varchar(15) UNIQUE NOT NULL,
   items_name varchar(30) NOT NULL,
   "version" integer NOT NULL,
   created_by text NOT NULL,
   created_at timestamp without time zone NOT NULL,
   updated_by text,
   updated_at timestamp without time zone,
   is_active boolean NOT NULL,
   FOREIGN KEY (files_id)
   	REFERENCES files(id),
   FOREIGN KEY (items_types_id)
   	REFERENCES items_types(id),
   FOREIGN KEY (items_brands_id)
   	REFERENCES items_brands(id)
);


CREATE TABLE companies(
	id varchar(36) PRIMARY KEY DEFAULT  uuid_generate_v4(),
	companies_code varchar(10) UNIQUE NOT NULL,
	companies_name varchar(255) NOT NULL,
	companies_phone varchar(14) NOT NULL,
	companies_address text NOT NULL,
	files_id varchar(36) NOT NULL,
	"version" integer NOT NULL,
	created_at timestamp WITHOUT time ZONE NOT NULL,
	created_by text NOT NULL,
	updated_at timestamp WITHOUT time ZONE,
	updated_by text,
	is_active bool NOT NULL
);


CREATE TABLE locations(
	id varchar(36) PRIMARY KEY DEFAULT  uuid_generate_v4(),
	locations_code varchar(10) UNIQUE NOT NULL,
	locations_deploy text,
	companies_id varchar(36) NOT NULL,
	"version" integer NOT NULL,
	created_at timestamp WITHOUT time ZONE NOT NULL,
	created_by text NOT NULL,
	updated_at timestamp WITHOUT time ZONE,
	updated_by text,
	is_active bool NOT NULL
);

CREATE TABLE invoices(
	id varchar(36) PRIMARY KEY DEFAULT  uuid_generate_v4(),
	invoices_code varchar(10) UNIQUE NOT NULL,
	invoices_date timestamp WITHOUT time ZONE NOT NULL,
	store_name varchar(50) NOT NULL,
	price numeric NOT NULL,
	"version" integer NOT NULL,
	created_at timestamp WITHOUT time ZONE NOT NULL,
	created_by text NOT NULL,
	updated_at timestamp WITHOUT time ZONE,
	updated_by text,
	is_active bool NOT NULL
);

CREATE TABLE statuses_in_out(
	id varchar(36) PRIMARY KEY DEFAULT  uuid_generate_v4(),
	statuses_in_out_code varchar (15) UNIQUE NOT NULL,
	statuses_in_out_name varchar(50) NOT NULL,
	"version" integer NOT NULL,
	created_by text NOT NULL,
	created_at timestamp WITHOUT time ZONE NOT NULL,
	updated_by text,
	updated_at timestamp WITHOUT time ZONE,
	is_active boolean NOT NULL
);


CREATE TABLE statuses_assets(
	id varchar(36) PRIMARY KEY DEFAULT  uuid_generate_v4(),
	statuses_assets_code varchar(5) UNIQUE NOT NULL,
	statuses_assets_name varchar(15) NOT NULL,
	"version" integer NOT NULL,
	created_at timestamp WITHOUT time ZONE NOT NULL,
	created_by text NOT NULL,
	updated_at timestamp WITHOUT time ZONE,
	updated_by text,
	is_active bool NOT NULL
);

CREATE TABLE assets(
	id varchar(36) PRIMARY KEY DEFAULT  uuid_generate_v4(),
	items_id varchar(36) NOT null,
	invoices_id varchar(36) NOT null,
	assets_name varchar(50) unique not null,
	statuses_assets_id varchar(36) NOT NULL,
	statuses_in_out_id varchar(36) NOT NULL,
	assets_expired date,
	"version" integer NOT NULL,
	created_by text NOT NULL,
	created_at timestamp WITHOUT time ZONE NOT NULL,
	updated_by text,
	updated_at timestamp WITHOUT time ZONE,
	is_active boolean NOT NULL,
	FOREIGN KEY (items_id)
	REFERENCES items(id),
	FOREIGN KEY (invoices_id)
	REFERENCES invoices(id),
	FOREIGN KEY (statuses_assets_id)
	REFERENCES statuses_assets(id),
	FOREIGN KEY (statuses_in_out_id)
	REFERENCES statuses_in_out(id)

);


CREATE TABLE statuses_transactions (
   id varchar(36) PRIMARY KEY DEFAULT  uuid_generate_v4(),
   statuses_assets_id varchar(36) NOT NULL,
   statuses_transactions_code varchar(15) UNIQUE NOT NULL,
   statuses_transactions_name varchar(30) NOT NULL,
   "version" integer NOT NULL,
   created_by text NOT NULL,
   created_at timestamp without time zone NOT NULL,
   updated_by text,
   updated_at timestamp without time zone,
   is_active boolean NOT NULL,
   FOREIGN KEY (statuses_assets_id)
	REFERENCES statuses_assets(id)
);


CREATE TABLE transactions_out (
   id varchar(36) PRIMARY KEY DEFAULT  uuid_generate_v4(),
   transactions_out_code varchar(15) UNIQUE NOT NULL,
   check_out_date timestamp without time zone NOT NULL,
   expired_date date NOT NULL,
   "version" integer NOT NULL,
   created_by text NOT NULL,
   created_at timestamp without time zone NOT NULL,
   updated_by text,
   updated_at timestamp without time zone,
   is_active boolean NOT NULL
);


CREATE TABLE transactions_detail_out(
	id varchar(36) PRIMARY KEY DEFAULT  uuid_generate_v4(),
	transactions_out_id varchar(36) NOT NULL,
	locations_id varchar(36),
   	employees_id varchar(36),
assets_general_id varchar(36),
	assets_id varchar(36) NOT NULL,
	transaction_detail_out_expired date NOT NULL,
	"version" integer NOT NULL,
	created_by text NOT NULL,
	created_at timestamp WITHOUT time ZONE NOT NULL,
	updated_by text,
	updated_at timestamp WITHOUT time ZONE,
	is_active boolean NOT NULL,
	FOREIGN KEY (locations_id)
REFERENCES locations (id),
FOREIGN KEY (employees_id)
REFERENCES employees (id),
FOREIGN KEY (assets_general_id)
REFERENCES assets (id),
FOREIGN KEY (transactions_out_id)
	REFERENCES transactions_out(id),
	FOREIGN KEY (assets_id)
	REFERENCES assets (id)
);


CREATE TABLE transactions_in(
	id varchar(36) PRIMARY KEY DEFAULT  uuid_generate_v4(),
	transactions_in_code varchar(15) UNIQUE NOT NULL,
	transactions_in_date timestamp WITHOUT time ZONE NOT NULL,
	transactions_out_id varchar(36) NOT NULL,
	"version" integer NOT NULL,
	created_at timestamp WITHOUT time ZONE NOT NULL,
	created_by text NOT NULL,
	updated_at timestamp WITHOUT time ZONE,
	updated_by text,
	is_active bool NOT NULL
);

CREATE TABLE transactions_detail_in(
	id varchar(36) PRIMARY KEY DEFAULT  uuid_generate_v4(),
	transactions_in_id varchar(36) NOT NULL,
	locations_id varchar(36),
   	employees_id varchar(36),
	Assets_general_id varchar(36),
	assets_id varchar(36) NOT NULL,
	statuses_transactions_id varchar(36) NOT NULL,
	return_date timestamp WITHOUT time ZONE,
	"version" integer NOT NULL,
	created_at timestamp WITHOUT time ZONE NOT NULL,
	created_by text NOT NULL,
	updated_at timestamp WITHOUT time ZONE,
	updated_by text,
	is_active bool NOT NULL
);

CREATE TABLE histories(
	id varchar(36) PRIMARY KEY DEFAULT uuid_generate_v4(),
	users_id varchar(36) NOT NULL,
	assets_id varchar(36) NOT NULL,
	activity_name varchar(30) NOT NULL,
	"version" integer NOT NULL,
	created_at timestamp WITHOUT time ZONE NOT NULL,
	created_by text NOT NULL,
	updated_at timestamp WITHOUT time ZONE,
	updated_by text,
	is_active bool NOT NULL,
	FOREIGN KEY (users_id)
REFERENCES users (id),
   	FOREIGN KEY (assets_id)
REFERENCES assets (id)
	
);

ALTER TABLE locations
	ADD CONSTRAINT companies_id_fk FOREIGN KEY(companies_id)
	REFERENCES companies(id);
	
Alter table transactions_in
	add constraint transactions_out_id_fk FOREIGN KEY(transactions_out_id)
	REFERENCES transactions_out(id);

Alter table transactions_detail_in
	add constraint transactions_in_id_fk FOREIGN KEY(transactions_in_id)
	REFERENCES transactions_in(id);

Alter table transactions_detail_in
	add constraint statuses_transactions_fk FOREIGN KEY(statuses_transactions_id)
	REFERENCES statuses_transactions(id);

Alter table transactions_detail_in
	add constraint employees_fk FOREIGN KEY(employees_id)
	REFERENCES employees(id);

Alter table transactions_detail_in
	add constraint locations_fk FOREIGN KEY(locations_id)
	REFERENCES locations(id);
	
Alter table transactions_detail_in
	add constraint assets_fk FOREIGN KEY(assets_id)
	REFERENCES assets(id);

Alter table transactions_detail_in
	add constraint assets_general_fk FOREIGN KEY(assets_general_id)
	REFERENCES assets(id);

ALTER TABLE companies
	ADD CONSTRAINT files_id_fk FOREIGN KEY(files_id)
	REFERENCES files(id);

