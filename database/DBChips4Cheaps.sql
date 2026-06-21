drop database Chips4Cheap;
create database Chips4Cheap;


use Chips4Cheap;

create table Account1(
       email  varchar(50) not null,
       username  varchar(50) not null,
       Password1 varchar(50) not null,
       Via  varchar(50) null,
       Cap  int null,
	   NumeroCivico int not null,
       Amministratore boolean not null default false,
       primary key(email)
);

create table Prodotto(
	NomeModello varchar(50) not null,
    Prezzo double not null,
	Descrizione varchar(5000) not null,
    Tipo varchar(50) not null,
    Quantità int not null,
	primary key(NomeModello)
);

create table Seleziona(
	email varchar(50) not null,
    NomeModello varchar(50) not null,
	foreign key(email) references Account1(email)on update cascade on delete cascade,
	foreign key(NomeModello) references Prodotto(NomeModello) on update cascade on delete cascade
);


create table RicevutaFiscale(
	IDRicevutaFiscale int not null auto_increment,
    email varchar(50) not null,
    metodoPagamento varchar(50) not null,
    DataEmissione Date not null,
    foreign key(email)references Account1(email) on update cascade, 
    primary key(email , IDRicevutaFiscale),
	Key(IDRicevutaFiscale)
);

create table ProdottoRicevuta(
    
	Prezzo double not null,
    email varchar(50) not null,
    NomeModello varchar(50) not null,
	Quantità int not null,
    foreign key(email) references RicevutaFiscale(email) on update cascade,
    primary key(email , NomeModello)
);