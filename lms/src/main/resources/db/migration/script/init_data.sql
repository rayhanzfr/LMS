INSERT INTO roles (role_code,names,created_by,created_date,isactive,version)VALUES
('SYSTEM','System',1,now(),true,0),
('ADMIN','Admin',1,now(),true,0),
('KASIR','Kasir',1,now(),true,0),
('KURIR','Kurir',1,now(),true,0);
INSERT INTO users (id_role,usernames,pass,email,created_by,created_date,isactive,version) VALUES
(2,'Admin','admin','admin@gmail.com',1,now(),true,0),
(3,'KASIR1','Kasir1','kasir1@gmail.com',1,now(),true,0),
(3,'KASIR2','kasir2','kasir2@gmail.com',1,now(),true,0),
(4,'KURIR1','kurir1','kurir1@gmail.com',1,now(),true,0),
(4,'KURIR2','kurir2','kurir2@gmail.com',1,now(),true,0),
(4,'KURIR3','kurir3','kusir3@gmail.com',1,now(),true,0),
(4,'KURIR4','kurir4','kurir4@gmail.com',1,now(),true,0);
INSERT INTO cities (city_code,names,created_by,created_date,isactive,version) VALUES
('CITY001','JAKARTA PUSAT',1,now(),true,0),
('CITY002','BOGOR',1,now(),true,0),
('CITY003','SEMARANG',1,now(),true,0),
('CITY004','MALANG',1,now(),true,0),
('CITY005','BANDUNG',1,now(),true,0);
INSERT INTO branches (id_city,branches_code,branches_name,created_by,created_date,isactive,version) VALUES
(1,'BRANCHES001','SHan Jakarta Pusat',1,now(),true,0),
(2,'BRANCHES002','SHan Bogor',1,now(),true,0),
(3,'BRANCHES003','SHan Semarang',1,now(),true,0),
(4,'BRANCHES004','SHan Malang',1,now(),true,0),
(5,'BRANCHES005','SHan Bandung',1,now(),true,0);
INSERT INTO profile (id_branch,id_user,profile_code,firstnames,lastnames,phonenumber,created_by,created_date,isactive,version) VALUES
(1,1,'PROFILE000','ADMIN','Admin','-',1,now(),true,0),
(1,2,'PROFILE001','Roy','Kiyoshi','081111111111',2,now(),true,0),
(2,3,'PROFILE002','Aden','Talas','081111111111',2,now(),true,0),
(1,4,'PROFILE003','Urep','Sudibjo','081111111111',2,now(),true,0),
(2,5,'PROFILE004','Joko','Kiyoshi','081111111111',2,now(),true,0),
(1,6,'PROFILE005','Rayhan','Kiyoshi','081111111111',2,now(),true,0),
(2,7,'PROFILE006','Asep','Kiyoshi','081111111111',2,now(),true,0);
INSERT INTO service_type(service_code,service_name,created_by,created_date,isactive,version)VALUES
('SRVC001','Platinum(Same Day)',1,now(),true,0),
('SRVC002','Gold(Next Day)',1,now(),true,0),
('SRVC003','Silver(Two Day)',1,now(),true,0),
('SRVC004','Bronze(Three Day)',1,now(),true,0),
('SRVC005','Metal(One Week)',1,now(),true,0);
INSERT INTO payment_method (payment_code,payment_name,created_by,created_date,isactive,version)VALUES
('PAY001','CASH',1,now(),true,0),
('PAY002','DEBIT',1,now(),true,0);

INSERT INTO default_price (id_service_type,id_branches,default_price_code,price,created_by,created_date,isactive,version)VALUES
(1,1,'DPRICE001',16400,1,now(),true,0),
(1,2,'DPRICE002',16300,1,now(),true,0),
(1,3,'DPRICE003',16200,1,now(),true,0),
(1,4,'DPRICE004',16100,1,now(),true,0),
(1,5,'DPRICE005',16000,1,now(),true,0),
(2,1,'DPRICE006',15400,1,now(),true,0),
(2,2,'DPRICE007',15300,1,now(),true,0),
(2,3,'DPRICE008',15200,1,now(),true,0),
(2,4,'DPRICE009',15100,1,now(),true,0),
(2,5,'DPRICE0010',15000,1,now(),true,0),
(3,1,'DPRICE0011',14400,1,now(),true,0),
(3,2,'DPRICE0012',14300,1,now(),true,0),
(3,3,'DPRICE0013',14200,1,now(),true,0),
(3,4,'DPRICE0014',14100,1,now(),true,0),
(3,5,'DPRICE0015',14000,1,now(),true,0),
(4,1,'DPRICE0016',11400,1,now(),true,0),
(4,2,'DPRICE0017',11300,1,now(),true,0),
(4,3,'DPRICE0018',11200,1,now(),true,0),
(4,4,'DPRICE0019',11100,1,now(),true,0),
(4,5,'DPRICE0020',10000,1,now(),true,0),
(5,1,'DPRICE0021',9400,1,now(),true,0),
(5,2,'DPRICE0022',9300,1,now(),true,0),
(5,3,'DPRICE0023',9200,1,now(),true,0),
(5,4,'DPRICE0024',9100,1,now(),true,0),
(5,5,'DPRICE0025',9000,1,now(),true,0);
INSERT INTO status(status_code, status_name, created_by, created_date, isactive,version)VALUES
('STATUS001', 'On Delivery', 1, now(), true,0),
('STATUS002', 'Delivered', 1, now(), true,0),
('STATUS003', 'Pending', 1, now(), true,0),
('STATUS004', 'MISROUTE', 1, now(), true,0),
('STATUS005', 'Criss Cross', 1, now(), true,0),
('STATUS006', 'Good', 1, now(), true,0),
('STATUS007', 'Broken', 1, now(), true,0),
('STATUS008', 'New Entry', 1, now(), true,0);
INSERT INTO categories(cat_code, names, created_by, created_date, isactive,version)VALUES
('CAT001', 'Elektronik', 1, now(), true,0),
('CAT002', 'Farmasi', 1, now(), true,0),
('CAT003', 'Apparel', 1, now(), true,0),
('CAT004', 'Food And Beverages', 1, now(), true,0),
('CAT005', 'Otomotif', 1, now(), true,0);