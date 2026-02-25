
-- creacion de la base de datos
CREATE DATABASE universidad
default character set utf8mb4
default collate utf8mb4_unicode_ci;

-- uso de la bd
use universidad;

-- Creacion se usuario solicitado y se le dan los privilegios CRUD
CREATE USER 'usuario_web'@'%' identified by 'la clave';
grant select, insert, update, delete on universidad.* to 'usuario_web'@'%';

flush privileges;

-- creacion de tablas
create table curso (
id_curso int not null auto_increment,
nombre varchar(100) not null,
description text,
creditos int not null check (creditos>0),
estado boolean,
imagen varchar(1024),
primary key (id_curso)
) ENGINE = InnoDB;

Create table estudiante(
id_estudiante int not null auto_increment,
nombre varchar(100) not null,
correo varchar(75) not null unique,
edad int check (edad>0),
id_curso int not null,
primary key (id_estudiante),
CHECK (correo REGEXP '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$'),
INDEX ndx_id_curso(id_curso),
foreign key fk_estudiante_curso (id_curso) references curso(id_curso)
) ENGINE = InnoDB;



