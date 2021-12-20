create or replace function bytea_import(p_path text, p_result out bytea) 
                       language plpgsql as $$
    declare
      l_oid oid;
    begin
      select lo_import(p_path) into l_oid;
      select lo_get(l_oid) INTO p_result;
      perform lo_unlink(l_oid);
    end;$$;

INSERT INTO roles (roles_code,roles_name,"version",created_by,created_at,is_active)VALUES
('ROLES1','SUPER-ADMIN',0,'1',now(),true),
('ROLES2','ADMIN',0,'1',now(),true),
('ROLES3','NON-ADMIN',0,'1',now(),true);

INSERT INTO users(roles_id, users_email,users_password,"version",created_by,created_at,is_active)
VALUES 
((select id from roles where roles_code = 'ROLES1'),'superadmin@gmail.com','superadmin',0,(select id from roles where roles_code = 'ROLES1'),now(),TRUE),
((select id from roles where roles_code = 'ROLES2'),'admin@gmail.com','superadmin',0,(select id from roles where roles_code = 'ROLES1'),now(),TRUE),
((select id from roles where roles_code = 'ROLES3'),'nonadmin@gmail.com','superadmin',0,(select id from roles where roles_code = 'ROLES1'),now(),TRUE);

INSERT INTO permissions (permissions_code,permissions_name,"version",created_by,created_at,is_active)VALUES
('PERMSN1','ROLES-READ',0,(select id from roles where roles_code = 'ROLES1'),now(),true);

INSERT INTO permissions_roles (permissions_id,roles_id,"version",created_by,created_at,is_active)VALUES
((select id from permissions where permissions_code = 'PERMSN1'),(select id from roles where roles_code = 'ROLES1'),0,(select id from roles where roles_code = 'ROLES1'),now(),true);

INSERT INTO items_types (items_types_code,items_types_name,"version",created_by,created_at,is_active)
VALUES ('ITMTYPES1','General',0,(select id from roles where roles_code = 'ROLES1'),now(),true),
('ITMTYPES2','Licenses',0,(select id from roles where roles_code = 'ROLES1'),now(),TRUE),
('ITMTYPES3','Component',0,(select id from roles where roles_code = 'ROLES1'),now(),TRUE),
('ITMTYPES4','Consumable',0,(select id from roles where roles_code = 'ROLES1'),now(),TRUE);

INSERT INTO invoices (invoices_code, invoices_date, store_name, price, "version",  created_at, created_by, is_active ) VALUES
	('INVC1', now(), 'TOKO A', 3000000, 0, now(), (select id from roles where roles_code = 'ROLES1'), true),
	('INVC2', now(), 'TOKO B', 5000000, 0, now(), (select id from roles where roles_code = 'ROLES1'), true);

INSERT INTO statuses_assets (statuses_assets_code, statuses_assets_name, "version",  created_at, created_by, is_active ) VALUES
	('DEP', 'DEPLOYABLE', 0, now(), (select id from roles where roles_code = 'ROLES1'), true),
	('UNDEP', 'UNDEPLOYABLE', 0, now(), (select id from roles where roles_code = 'ROLES1'), true),
	('ARCHV', 'ARCHIVED', 0, now(), (select id from roles where roles_code = 'ROLES1'), true),
	('PEND', 'PENDING', 0, now(), (select id from roles where roles_code = 'ROLES1'), true);

INSERT INTO files (file,extensions,created_by,created_at,is_active,"version")VALUES

(bytea_import('D:\Lawencon\FINAL PRJ\backend\LMS-main\LMS-main\bootcamp\lms\src\main\resources\reports\logoLMS 1.svg'),'svg',(select id from roles where roles_code = 'ROLES1'),now(),true,0);



INSERT INTO items_brands (items_brands_code,items_brands_name,"version",created_by,created_at,is_active)VALUES
('ITMBRANDS1','LENOVO',0,(select id from roles where roles_code = 'ROLES1'),now(),true);

INSERT INTO items (files_id, items_types_id,items_brands_id,items_code,items_name,"version",created_by,created_at,is_active)VALUES
((select id from files where extensions = 'svg'),(select id from items_types where items_types_code= 'ITMTYPES1'),(select id from items_brands where items_brands_code='ITMBRANDS1'),'ITEMS1','LAPTOP',0,(select id from roles where roles_code = 'ROLES1'),now(),true);

INSERT INTO companies (files_id, companies_code, companies_name, companies_phone, companies_address, "version",  created_at, created_by, is_active ) VALUES
	((select id from files where extensions = 'svg'),'COMP1', 'Lawencon', '02128542549', 'Pakuwon Tower 22nd floor Jl Casablanca Kav 88, Jakarta Selatan DKI Jakarta', 0,now(), (select id from roles where roles_code = 'ROLES1'), true),
	((select id from files where extensions = 'svg'),'COMP2', 'PT. LINOV ROKET PRESTASI', ' 02128542835', 'Pakuwon Tower 22nd floor Jl Casablanca Kav 88, Jakarta Selatan DKI Jakarta', 0, now(), (select id from roles where roles_code = 'ROLES1'), true);

INSERT INTO locations (locations_code, locations_deploy, companies_id, "version",  created_at, created_by, is_active ) VALUES
	('LOCTN1', 'Bootcamp', (select id from companies where companies_code= 'COMP1'), 0, now(), (select id from roles where roles_code = 'ROLES1'), true),
	('LOCTN2', 'Tomcat', (select id from companies where companies_code= 'COMP1'), 0, now(), (select id from roles where roles_code = 'ROLES1'), true);

INSERT INTO statuses_transactions (statuses_assets_id,statuses_transactions_code,statuses_transactions_name,"version",created_by,created_at,is_active)VALUES
((select id from statuses_assets where statuses_assets_code= 'DEP'),'READY','Ready To Deploy',0,(select id from roles where roles_code = 'ROLES1'),now(),true);

INSERT INTO employees (users_id,companies_id, employees_code, employees_fullname, employees_address,employees_phone_number,"version",created_by,created_at,is_active)
VALUES 
((select id from users where users_email = 'superadmin@gmail.com'),(select id from companies where companies_code = 'COMP1'),'EMP1','super admin','jatinegara','0823123945',0,(select id from roles where roles_code = 'ROLES1'),now(),true),
((select id from users where users_email = 'admin@gmail.com'),(select id from companies where companies_code = 'COMP1'),'EMP2','admin','kalibata','08239141234',0,(select id from roles where roles_code = 'ROLES1'),now(),TRUE),
((select id from users where users_email = 'nonadmin@gmail.com'),(select id from companies where companies_code = 'COMP1'),'EMP3','non admin','cakung','0813284923',0,(select id from roles where roles_code = 'ROLES1'),now(),TRUE);

INSERT INTO statuses_in_out (statuses_in_out_code,statuses_in_out_name,"version",created_by,created_at,is_active)
VALUES 
('CIN','Check In',0,(select id from roles where roles_code = 'ROLES1'),now(),TRUE),
('COUT','Check Out',0,(select id from roles where roles_code = 'ROLES1'),now(),TRUE);

