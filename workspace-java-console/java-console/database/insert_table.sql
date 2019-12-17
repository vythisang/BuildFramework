
insert into role(code,name) values('MANAGER','Quản lý');
insert into role(code,name) values('STAFF','Nhân viên');

insert into user(username,password,fullname,status) values('nguyenvana','123456','Nguyễn Văn A',1);
insert into user(username,password,fullname,status) values('nguyenvanb','123456','Nguyễn Văn B',1);
insert into user(username,password,fullname,status) values('nguyenvanc','123456','Nguyễn Văn c',1);
insert into user(username,password,fullname,status) values('nguyenvand','123456','Nguyễn Văn d',1);

insert into user_role(userid,roleid) values(1,1);
insert into user_role(userid,roleid) values(2,2);
insert into user_role(userid,roleid) values(3,2);
insert into user_role(userid,roleid) values(4,2);


insert into building(name,numberofbasement,buildingarea,district,ward,street,costrent,costdescription,managername,managerphone,type) values('FCL Building',2,500,'Quan_1','Phường 2','59 Phan Xích Long',15,'15 triệu/m2','Hùng','012345678','TANG_TRET,NGUYEN_CAN');
insert into building(name,numberofbasement,buildingarea,district,ward,street,costrent,costdescription,managername,managerphone,type) values('ACM Tower',2,650,'Quan_2','Phường 4','96 Cao Thắng',18,'18 triệu/m2','Cường','012345678','NGUYEN_CAN');
insert into building(name,numberofbasement,buildingarea,district,ward,street,costrent,costdescription,managername,managerphone,type) values('Alpha 2 Building',1,200,'Quan_3','Phường 6','153 Nguyễn Đình Chiểu',20,'20 triệu/m2','Huy','012345678','TANG_TRET,NGUYEN_CAN,NOI_THAT');
insert into building(name,numberofbasement,buildingarea,district,ward,street,costrent,costdescription,managername,managerphone,type) values('IDO 1 Building',1,200,'Quan_4','Phường 7','111 Lý Chánh Thắng',12,'12 triệu/m2','Vy','012345678','NOI_THAT');
insert into building(name,numberofbasement,buildingarea,district,ward,street,costrent,costdescription,managername,managerphone,type) values('VINACONTEXT',1,150,'Quan_3','Phường 10','134/1 Cách Mạng Thánh 8',1000,'','Mai','012345678','2019-8-22 10:34:09','NGUYEN_CAN,NOI_THAT');	
