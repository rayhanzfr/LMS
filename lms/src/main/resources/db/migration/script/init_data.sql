INSERT INTO roles (roles_code,roles_name,"version",created_by,created_date,is_active)VALUES
('ROLES001','SUPER-ADMIN',0,'1',now(),true);

INSERT INTO users(roles_id, users_email,users_password,"version",created_by,created_at,is_active)
VALUES (roles_id,"superadmin@gmail.com","superadmin",0,"1",now(),TRUE),
(roles_id,"admin@gmail.com","admin",0,"1",now(),TRUE),
(roles_id,"nonadmin@gmail.com","nonadmin",0,"1",now(),TRUE);

INSERT INTO permissions (permissions_code,permissions_name,"version",created_by,created_date,is_active)VALUES
('PERMISSIONS001','ROLES-READ',0,'1',now(),true);

INSERT INTO permissions_roles (permissions_id,roles_id,"version",created_by,created_date,is_active)VALUES
('permissions_id_x','roles_id_x',0,'1',now(),true);

INSERT INTO employees (users_id, employees_fullname, employees_address,employees_phone_number,"version",created_by,created_at,is_active)
VALUES (users_id,"super admin","jatinegara","0823123945",0,"1",now(),true),
(users_id,"admin","kalibata","08239141234",0,"1",now(),TRUE),
(users_id,"non admin","cakung","0813284923",0,"1",now(),TRUE);	

INSERT INTO items_types (items_types_code,items_types_name,"version",created_by,created_at,is_active)
VALUES ("GNRL","General",0,"1",now(),true),
("LCNS","Licenses",0,"1",now(),TRUE),
("COMP","Component",0,"1",now(),TRUE),
("CONSUM","Consumable",0,"1",now(),TRUE);


INSERT INTO items (items_types_id,items_code,items_name,"version",created_by,created_at,is_active)VALUES
(‘items_types_id_x’,'ITEMS001','LAPTOP',0,'1',now(),true);

INSERT INTO statuses_in_out (statuses_in_out_code,statuses_in_out_name,"version",created_by,created_at,is_active)
VALUES ("CIN","Check In",0,"1",now(),TRUE),
("CON","Check Out",0,"1",now(),TRUE);

INSERT INTO assets (items_id,invoices_id,assets_name,statuses_assets_id,statuses_in_out_id,assets_expired,"version",created_by,created_at,is_active)
VALUES (items_id,invoices_id,"Laptop-AS-1231",statuses_assets_id,statuses_in_out_id,now(),0,"1",now(),TRUE),
(items_id,invoices_id,"Laptop-AS-12235",statuses_assets_id,statuses_in_out_id,now(),0,"1",now(),TRUE),
(items_id,invoices_id,"Laptop-AS-12we9",statuses_assets_id,statuses_in_out_id,now(),0,"1",now(),TRUE);

INSERT INTO statuses_transactions (statuses_assets_id,status_transactions_code,status_transactions_name,"version",created_by,created_at,is_active)VALUES
(‘statuses_assets_id_x’,'STSTR001','Ready To Deploy',0,'1',now(),true);

[
INSERT INTO transactions_out (locations_id,check_out_date,expired_date,"version",created_by,created_at,is_active)VALUES
('locations_id_x','TROUT001','Ready To Deploy',0,'1',now(),true);

INSERT INTO transactions_out (employees_id,check_out_date,expired_date,"version",created_by,created_at,is_active)VALUES
('employees_id_x','TROUT002','Ready To Deploy',0,'1',now(),true);

INSERT INTO transactions_out (assets_id,check_out_date,expired_date,"version",created_by,created_at,is_active)VALUES
('assets_id_x','TROUT003','Ready To Deploy',0,'1',now(),true);
]

INSERT INTO transactions_detail_out (transactions_out_id,assets_id,transaction_detail_out_expired,"version",created_by,created_at,is_active)
VALUES (transactions_out_id,assets_id,now(),0,"1",now(),true),
(transactions_out_id,assets_id,now(),0,"1",now(),true),
(transactions_out_id,assets_id,now(),0,"1",now(),true);

INSERT INTO companies (companies_code, companies_name, companies_phone, companies_address, "version",  created_at, created_by, is_active ) VALUES
	('LAW1', 'Lawencon', '02128542549', 'Pakuwon Tower 22nd floor Jl Casablanca Kav 88, Jakarta Selatan DKI Jakarta', 0,now(), 'uuid_x', true),
	('LIN1', 'PT. LINOV ROKET PRESTASI', ' 02128542835', 'Pakuwon Tower 22nd floor Jl Casablanca Kav 88, Jakarta Selatan DKI Jakarta', 0, now(), 'uuid_x', true);
	
INSERT INTO locations (locations_code, locations_deploy, companies_id, "version",  created_at, created_by, is_active ) VALUES
	('BOOT1', 'Bootcamp', 'companies_id_x', 0, now(), 'uuid_x', true),
	('TOM1', 'Tomcat', ' companies_id_x', 0, now(), 'uuid_x', true);
	
INSERT INTO invoices (invoices_code, invoices_date, store_name, price, "version",  created_at, created_by, is_active ) VALUES
	('AAAAA1', now(), 'TOKO A', 3000000, 0, now(), 'uuid_x', true),
	('AAAAA2', now(), 'TOKO B', 5000000, 0, now(), 'uuid_x', true);
	
INSERT INTO statuses_assets (statuses_assets_code, statuses_assets_name, "version",  created_at, created_by, is_active ) VALUES
	('DEP', 'DEPLOYABLE', 0, now(), '1', true),
	('UNDEP', 'UNDEPLOYABLE', 0, now(), '1', true),
	('ARCHV', 'ARCHIVED', 0, now(), '1', true),
	('PEND', 'PENDING', 0, now(), '1', true);
	
INSERT INTO transactions_in (transactions_in_code, transactions_in_date, transactions_out_id, "version",  created_at, created_by, is_active ) VALUES
	('TRANS1', now(), 'transactions_out_id_x', 0, now(), 'uuid_x', true),
	('TRANS2', now(), 'transactions_out_id_x', 0, now(), 'uuid_x', true);
	
INSERT INTO transactions_detail_in (transactions_id, items_id, statuses_transactions_id, return_date, "version",  created_at, created_by, is_active ) VALUES
	('transactions_id_x', 'items_id_x', 'statuses_transactions_id_x', now(), 0, now(), 'uuid_x', true),
	('transactions_id', 'items_id_x', 'statuses_transactions_id_x', now(), 0, now(), 'uuid_x', true);
