
INSERT INTO roles (roles_code,roles_name,"version",created_by,created_at,is_active)VALUES
('ROLES1','SYSTEM',0,'1',now(),true);

INSERT INTO users(roles_id, users_email,users_password,"version",created_by,created_at,is_active)
VALUES 
((select id from roles where roles_code = 'ROLES1'),'lawenconassetsmanagement@gmail.com','$2a$10$Ggcg/Qi3Ht7oU.4qCGnkkOZa3KiS00ws.4Hq6U2uRN65nMmiykhYW',0,'1',now(),TRUE);

INSERT INTO roles (roles_code,roles_name,"version",created_by,created_at,is_active)VALUES
('ROLES2','SUPER-ADMIN',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('ROLES3','NON-ADMIN',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true);

INSERT INTO users(roles_id, users_email,users_password,"version",created_by,created_at,is_active)
VALUES 
((select id from roles where roles_code = 'ROLES2'),'rayhanzfr@gmail.com','$2a$10$Ggcg/Qi3Ht7oU.4qCGnkkOZa3KiS00ws.4Hq6U2uRN65nMmiykhYW',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),TRUE);

INSERT INTO permissions (permissions_code,permissions_name,"version",created_by,created_at,is_active)VALUES
('PERMSN1','ROLES-READ',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN2','ROLES-CREATE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN3','ROLES-UPDATE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN4','ROLES-DELETE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN5','PERMISSIONS-READ',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN6','PERMISSIONS-CREATE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN7','PERMISSIONS-UPDATE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN8','PERMISSIONS-DELETE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN9','ASSETS-READ',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN10','ASSETS-CREATE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN11','ASSETS-UPDATE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN12','ASSETS-DELETE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN13','COMPANIES-READ',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN14','COMPANIES-CREATE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN15','COMPANIES-UPDATE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN16','COMPANIES-DELETE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN17','EMPLOYEES-READ',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN18','EMPLOYEES-CREATE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN19','EMPLOYEES-UPDATE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN20','EMPLOYEES-DELETE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN21','INVOICES-READ',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN22','INVOICES-CREATE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN23','INVOICES-UPDATE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN24','INVOICES-DELETE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN25','ITEMS-READ',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN26','ITEMS-CREATE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN27','ITEMS-UPDATE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN28','ITEMS-DELETE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN29','ITEMSBRANDS-READ',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN30','ITEMSBRANDS-CREATE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN31','ITEMSBRANDS-UPDATE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN32','ITEMSBRANDS-DELETE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN33','TRANSACTIONSOUT-READ',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN34','TRANSACTIONSOUT-CREATE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN35','TRANSACTIONSIN-READ',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN36','TRANSACTIONSIN-CREATE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN37','USERS-READ',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN38','USERS-CREATE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN39','USERS-UPDATE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN40','USERS-DELETE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN41','ITEMSTYPES-READ',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN42','ITEMSTYPES-CREATE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN43','ITEMSTYPES-UPDATE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN44','ITEMSTYPES-DELETE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN45','STATUSESASSETS-READ',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN46','STATUSESASSETS-CREATE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN47','STATUSESASSETS-UPDATE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN48','STATUSESASSETS-DELETE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN49','STATUSESINOUT-READ',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN50','STATUSESINOUT-CREATE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN51','STATUSESINOUT-UPDATE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN52','STATUSESINOUT-DELETE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN53','LOCATIONS-READ',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN54','LOCATIONS-CREATE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN55','LOCATIONS-UPDATE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
('PERMSN56','LOCATIONS-DELETE',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true);




INSERT INTO permissions_roles (permissions_id,roles_id,"version",created_by,created_at,is_active)VALUES
((select id from permissions where permissions_code = 'PERMSN1'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN2'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN3'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN4'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN5'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN6'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN7'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN8'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN9'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN10'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN11'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN12'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN13'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN14'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN15'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN16'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN17'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN18'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN19'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN20'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN21'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN22'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN23'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN24'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN25'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN26'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN27'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN28'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN29'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN30'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN31'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN32'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN33'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN34'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN35'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN36'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN37'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN38'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN39'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN40'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN41'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN42'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN43'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN44'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN45'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN46'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN47'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN48'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN49'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN50'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN51'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN52'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN53'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN54'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN55'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN56'),(select id from roles where roles_code = 'ROLES1'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN1'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN2'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN3'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN4'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN5'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN6'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN7'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN8'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN9'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN10'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN11'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN12'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN13'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN14'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN15'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN16'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN17'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN18'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN19'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN20'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN21'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN22'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN23'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN24'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN25'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN26'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN27'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN28'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN29'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN30'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN31'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN32'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN33'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN34'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN35'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN36'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN37'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN38'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN39'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN40'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN41'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN42'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN43'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN44'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN45'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN46'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN47'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN48'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN49'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN50'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN51'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN52'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN53'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN54'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN55'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN56'),(select id from roles where roles_code = 'ROLES2'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN9'),(select id from roles where roles_code = 'ROLES3'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN10'),(select id from roles where roles_code = 'ROLES3'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN13'),(select id from roles where roles_code = 'ROLES3'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN17'),(select id from roles where roles_code = 'ROLES3'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN18'),(select id from roles where roles_code = 'ROLES3'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN19'),(select id from roles where roles_code = 'ROLES3'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN21'),(select id from roles where roles_code = 'ROLES3'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN22'),(select id from roles where roles_code = 'ROLES3'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN25'),(select id from roles where roles_code = 'ROLES3'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN29'),(select id from roles where roles_code = 'ROLES3'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN33'),(select id from roles where roles_code = 'ROLES3'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN34'),(select id from roles where roles_code = 'ROLES3'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN35'),(select id from roles where roles_code = 'ROLES3'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN36'),(select id from roles where roles_code = 'ROLES3'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN41'),(select id from roles where roles_code = 'ROLES3'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN45'),(select id from roles where roles_code = 'ROLES3'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN49'),(select id from roles where roles_code = 'ROLES3'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
((select id from permissions where permissions_code = 'PERMSN53'),(select id from roles where roles_code = 'ROLES3'),0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true);

INSERT INTO items_types (items_types_code,items_types_name,"version",created_by,created_at,is_active) VALUES 
	('ITMTYPES1','General',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
	('ITMTYPES2','Licenses',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),TRUE),
	('ITMTYPES3','Component',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),TRUE),
	('ITMTYPES4','Consumable',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),TRUE);

INSERT INTO statuses_assets (statuses_assets_code, statuses_assets_name, "version",  created_at, created_by, is_active ) VALUES
	('DEP', 'DEPLOYABLE', 0, now(),(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'), true),
	('UNDEP', 'UNDEPLOYABLE', 0, now(),(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'), true),
	('ARCHV', 'ARCHIVED', 0, now(), (select id from users where users_email = 'lawenconassetsmanagement@gmail.com'), true),
	('PEND', 'PENDING', 0, now(), (select id from users where users_email = 'lawenconassetsmanagement@gmail.com'), true);

INSERT INTO statuses_transactions (statuses_assets_id,statuses_transactions_code,statuses_transactions_name,"version",created_by,created_at,is_active) VALUES
	((select id from statuses_assets where statuses_assets_code= 'DEP'),'READY','Ready To Deploy',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
	((select id from statuses_assets where statuses_assets_code= 'UNDEP'),'BROKEN','Broken',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
	((select id from statuses_assets where statuses_assets_code= 'ARCHV'),'LOST/STOLEN','Lost or Stolen',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true),
	((select id from statuses_assets where statuses_assets_code= 'UNDEP'),'REPAIR','Repair',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),true);

INSERT INTO statuses_in_out (statuses_in_out_code,statuses_in_out_name,"version",created_by,created_at,is_active) VALUES 
	('CIN','Check In',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),TRUE),
	('COUT','Check Out',0,(select id from users where users_email = 'lawenconassetsmanagement@gmail.com'),now(),TRUE);

INSERT INTO companies (companies_code, companies_name, companies_phone, companies_address, "version",  created_at, created_by, is_active ) VALUES
	('COMP1', 'Lawencon International', '02128542549', 'Pakuwon Tower 22nd floor Jl Casablanca Kav 88, Jakarta Selatan DKI Jakarta', 0 ,now(), (select id from users where users_email = 'lawenconassetsmanagement@gmail.com'), true);
