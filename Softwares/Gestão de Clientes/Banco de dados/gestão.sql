/** 
Gestão de Clientes
Senac Tatuapé TI
@author: Cleverson Martins
26/02/2022 
*/

create database cmpi;
use cmpi;

create table usuarios(
	id int primary key auto_increment,
	usuario varchar(70) not null,
	login varchar(20) not null unique,
	senha varchar(250) 
);

insert into usuarios(usuario,login,senha) values ('Íris Ortega','irisorth@live.com',md5('215465'));
insert into usuarios(usuario,login,senha) values ('Hector de Azevedo','hector_aze@gmail.com',md5('562480'));
insert into usuarios(usuario,login,senha) values ('Flor de Liz','flor.liz@hotmail.com',md5('685441'));
insert into usuarios(usuario,login,senha) values ('Cleverson Martins','clev.mts@gmail.com',md5('657720'));

insert into usuarios (usuario, login, senha)
values ('Clever', 'admin' , '1234');

select * from usuarios;
describe usuarios;
select * from usuarios where login='irisorth@live.com' and senha=md5('215465');

create table clientes(
	idcli int primary key auto_increment,
    nome varchar(70) not null,
    cpf varchar(20) unique not null,
    fone varchar(20) not null,
    email varchar(50) unique,
    cep varchar(20) not null,
    endereco varchar(120) not null,
    numero varchar(50) not null,
    complemento varchar(120),
    cidade varchar(50) not null,
    bairro varchar(50) not null,
    estado varchar(70) not null
);

insert into clientes (nome,cpf,fone,email,cep,endereco,numero,complemento,cidade,bairro,estado) 
values ('Henrique de Almeida','20225485265','97232-3200','henrique@hotmail.com','17010-001','Rua Primeiro de Maio','23','Casa de muro verde','Bauru','Estoril','São Paulo');

insert into clientes (nome,cpf,fone,email,cep,endereco,numero,complemento,cidade,bairro,estado) 
values ('Flávio de Campos','20589485105','92532-3210','flavio@gmail.com','06070-1200','Avenida São João','245','Paralelo a rua XXI','Sete Lagoas','Dumont','Minas Gerais');

insert into clientes (nome,cpf,fone,email,cep,endereco,numero,complemento,cidade,bairro,estado) 
values ('Bruna Ortega','28585625105','93372-3020','bruna@bol.com.br','02140-302','Estrada das Flores','1254','Sobrado Amarelo','Jacarezinho','Niterói','Rio de Janeiro');

insert into clientes (nome,cpf,fone,email,cep,endereco,numero,complemento,cidade,bairro,estado) 
values ('Carol Ribeiro','22385625225','91172-2220','carol@ig.com.br','63540-250','Rua 25','247','Frente ao Mercado','Manaus','Manacapuru','Amazonas');

insert into clientes (nome,cpf,fone,email,cep,endereco,numero,complemento,cidade,bairro,estado) 
values ('Bruna Fratesk','15385623525','95972-2215','thibo@gmail.com','65240-230','Pio XXIII','2450','Esquina com a Fiat','Nova Andradina','Ingá','Mato Grosso do Sul');

select * from clientes;
describe clientes;
select * from clientes where nome like 'b%' order by nome;





