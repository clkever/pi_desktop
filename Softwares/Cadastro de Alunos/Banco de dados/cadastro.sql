/**
Cadastro de Clientes
Senac Tatuapé TI 
@author Cleverson Martins
10/03/2022
*/

create database cadastro;
use cadastro;

create table contatos(
	id int primary key auto_increment,
    nome varchar(150) not null,
    fone varchar(20) not null,
    email varchar(70) unique
);

insert into contatos (nome,fone,email)
values ('Ana Maria','99999-4444','ana@email.com');

insert into contatos (nome,fone,email)
values ('Bruna','99999-11111','bruna@email.com');

insert into contatos (nome,fone,email)
values ('Bianca','99999-22222','bianca@email.com');

insert into contatos (nome,fone,email)
values ('Bruno','99999-33333','bruno@email.com');

insert into contatos (nome,fone,email)
values ('Barbara','99999-44444','barbara@email.com');

insert into contatos (nome,fone,email)
values ('Tiago','99999-55555','tiago@email.com');

insert into contatos (nome,fone,email)
values ('Rodrigo','99999-66666','rodrigo@email.com');

insert into contatos (nome,fone,email)
values ('Flávio','99999-77777','flavio@email.com');

insert into contatos (nome,fone,email)
values ('Sebastião','99999-88888','sebastiao@email.com');

insert into contatos (nome,fone)
values ('Paulo','99999-99999');

insert into contatos (nome,fone)
values ('Raquel','99999-100000');

insert into contatos (nome,fone)
values ('Beatriz' , ' 99999-222');

select * from contatos;
select * from contatos order by nome;

























   






