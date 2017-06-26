create table usuario(
	nome varchar(50),
	sobrenome varchar (50),
	email varchar (60),
	rg varchar (11),
	cpf varchar (16),
	nascimento varchar(10),
	usuario varchar (100),
	senha varchar (100),
	primary key (cpf)
	
);

create table drivers(
	idDriver serial primary key,
	nomeDriver varchar(100) unique,
	descricao varchar(255),
	fabricante varchar(100),
	pack varchar(20),
	uri varchar(255) unique,
	nomeJar varchar(50)unique,
	nomePacote varchar(50)unique,
	nomeClasse varchar(50)unique
);

create table sensores(
	idDriver integer not null,
	ip varchar(50) unique,
	idSensor varchar(50) unique,
	foreign key (idDriver) references drivers (idDriver)
);

até aqui banco do sistema
/***************************************************************************************/
comandos para testes

drop table usuario

select *from usuario

insert into usuario (usuario,senha,cpf) values ('admin','jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=','00')



select *from drivers
select iddriver,uri,nomepacote,nomeclasse from drivers where iddriver = 6

drop table drivers

DELETE FROM drivers

insert into drivers (nomeDriver,descricao,fabricante,pack,uri,nomeJar,nomePacote,nomeClasse)
values('nomeDriver','descricaoooo','fabricanteee','packpackpack','C://url','jar.jar','pacotepacotera','essaclasse');



select 
delete from sensores where iddriver=1
select *from sensores, drivers where idsensor='E11wf' and i

insert into sensores values (6,'192.168.5.3','991wf');

select *from sensores,drivers where sensores.iddriver = drivers.iddriver and sensores.idsensor='E11wf'
