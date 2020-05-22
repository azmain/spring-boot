insert into SECU_USERS(ID,NAME,USER_NAME,EMAIL,PASSWORD)
values(1,'Nishan', 'nishan', 'nishan@gmail.com', 'nishan');

insert into SECU_ROLES
values(1,'ADMIN');
-- insert into SECU_ROLES
-- values(2,'USER');

insert into SECU_USER_ROLES
values(1,1);
