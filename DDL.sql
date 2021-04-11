create schema ecommerce;

use ecommerce;

create user 'user'@'localhost' identified by 'pass123';

grant select, insert, delete, update on ecommerce.* to user@'localhost';

-- Cria tabela cliente
create table cli_cliente (
  cli_id bigint unsigned not null auto_increment,
  cli_nome varchar(20) not null,
  cli_email varchar(50) not null,
  cli_idade int not null,
  
  PRIMARY KEY (cli_id),
  UNIQUE KEY uni_cli_email (cli_email)  
);

-- Cria tabela Pedido
create table ped_pedido (
  ped_id bigint unsigned not null auto_increment,
  ped_nome varchar(50) not null,
  ped_valor int not null,
  cli_id bigint unsigned not null,
  primary key (ped_id),  
  unique key uni_ped_nome (ped_nome),
  foreign key pedcli_cli_fk (cli_id) references cli_cliente (cli_id) on delete restrict on update cascade
);

insert into cli_cliente(cli_nome, cli_email, cli_idade)
    values('Erica', 'erica@email.com', 37);
insert into cli_cliente(cli_nome, cli_email, cli_idade)
    values('Erica1', 'erica1@email.com', 37);



insert into ped_pedido(ped_nome, ped_valor, cli_id)
    values('pedido01', 200, 1);
insert into ped_pedido(ped_nome, ped_valor, cli_id)
    values('pedido02', 200, 2);
