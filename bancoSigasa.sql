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
	versao varchar(20),
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
/IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII/
aqui pra baixo comandos para testes
select *from
select  sensores.idsensor, sensores.iddriver , drivers.iddriver, nomedriver  from sensores, drivers where drivers.iddriver = sensores.iddriver 


select * from 
select distinct drivers.iddriver, nomedriver, count(sensores.iddriver = drivers.iddriver)ss from drivers, sensores where drivers.iddriver = sensores.iddriver 

select * from sensores

select count( from drivers, sensores where drivers.iddriver = sensores.iddriver

drop table usuario

select *from usuario

insert into usuario (usuario,senha,cpf) values ('admin','jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=','00')



select *from drivers
select iddriver,uri,nomepacote,nomeclasse from drivers where iddriver = 6
drop table sensores
drop table drivers

DELETE FROM drivers

insert into drivers (nomeDriver,descricao,fabricante,pack,uri,nomeJar,nomePacote,nomeClasse)
values('nomeDriver','descricaoooo','fabricanteee','packpackpack','C://url','jar.jar','pacotepacotera','essaclasse');



select 
delete from sensores where iddriver=1
select *from sensores, drivers where idsensor='E11wf' and i

insert into sensores values (6,'192.168.5.3','991wf');

select *from sensores,drivers where sensores.iddriver = drivers.iddriver and sensores.idsensor='E11wf'

create table algo(
  id  serial,
  nome varchar(10) not null,
  primary key(id));

drop table algo
  insert into algo (id, nome) values (0,'Teste');
  insert into algo (id,nome) values (1,'Teste');
  insert into algo (id,nome) values (3,'Teste2');

select *from algo

  select distinct concat(nome,'(',(select count(id)id from algo),')') as nome from algo;
  select distinct concat(nome,'(',count(id),')') as nome from algo;
  select distinct sum(id-id) as id, concat(nome,'(',count(id),')') as nome from algo group by nome;


select drivers.iddriver, nomedriver from sensores, drivers where drivers.iddriver = sensores.iddriver group by drivers.iddriver;

select distinct count(drivers.iddriver) as sensores ,drivers.iddriver, nomedriver   from sensores RIGHT OUTER JOIN  drivers on drivers.iddriver = sensores.iddriver group by drivers.iddriver;

select distinct count(drivers.iddriver) as sensores ,drivers.iddriver as iddriver, nomedriver   from sensores ,  drivers where drivers.iddriver = sensores.iddriver group by drivers.iddriver;
