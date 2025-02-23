create database BYTS;
create user 'testuser'@'%' identified by 'password';
grant all on BYTS.* to 'testuser'@'%';
