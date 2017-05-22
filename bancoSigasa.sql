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

drop table usuario

select *from usuario

insert into usuario (usuario,senha,cpf) values ('admin','jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=','00')