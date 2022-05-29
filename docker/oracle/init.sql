-- https://stackoverflow.com/a/23955869/1939921
create tablespace my_user_tabspace
    datafile 'my_user_tabspace.dat'
    size 10 M autoextend on;

create temporary tablespace my_user_tabspace_temp
    tempfile 'my_user_tabspace_temp.dat'
    size 5 M autoextend on;

create user my_user
    identified by my_password
    default tablespace my_user_tabspace
    temporary tablespace my_user_tabspace_temp;

grant create session to my_user;
grant create table to my_user;
grant create sequence to my_user;
grant unlimited tablespace to my_user;
