drop database Chips4Cheap;
create database Chips4Cheap;


use Chips4Cheap;

create table Account1(
       email  varchar(50) not null,
       username  varchar(50) not null,
       Password1 char(128) not null,
       Via  varchar(50) not null,
       Cap  char(5) not null,
	   NumeroCivico int not null,
       Amministratore boolean not null default false,
       primary key(email)
);

create table Prodotto(
	NomeModello varchar(50) not null,
    Produttore varchar(50) not null,
    Prezzo double not null,
	Descrizione varchar(5000) not null,
    Tipo varchar(50) not null,
    Quantità int not null,
    Image varchar(50) not null,
	primary key(NomeModello)
);

create table RicevutaFiscale(
	IDRicevutaFiscale int not null auto_increment,
    email varchar(50) not null,
    metodoPagamento varchar(50) not null,
    Via  varchar(50) not null,
    Cap  char(5) not null,
	NumeroCivico int not null,
    DataEmissione Date not null,
    foreign key(email)references Account1(email) on update cascade on delete cascade, 
    primary key(email , IDRicevutaFiscale),
	Key(IDRicevutaFiscale)
);


create table ProdottoRicevuta(
	Prezzo double not null,
    Produttore varchar(50) not null,
    email varchar(50) not null,
    NomeModello varchar(50) not null,
    image varchar(50) not null,
    tipo varchar(50) not null,
    primary key(NomeModello)
);


create table ha(
	IDRicevutaFiscale int not null auto_increment,
	Email varchar(50) not null,
    NomeModello varchar(50) not null,
    foreign key(Email,IDRicevutaFiscale) references RicevutaFiscale(Email,IDRicevutaFiscale) on update cascade on delete cascade,
    foreign key(NomeModello)references ProdottoRicevuta(NomeModello) on update cascade on delete cascade,
    primary key(Email,IDRicevutaFiscale,NomeModello),
    Key(IDRicevutaFiscale)
);