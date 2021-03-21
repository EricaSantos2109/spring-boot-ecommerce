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
  primary key (ped_id),  
  unique key uni_ped_nome (ped_nome)
);

create table tab_cliente_pedido(
  tab_cli_id bigint unsigned not null,
  tab_ped_id bigint unsigned not null,
  primary key (tab_cli_id, tab_ped_id),
  foreign key tab_cliente_fk (tab_cli_id) references cli_cliente (cli_id) on delete restrict on update cascade,
  foreign key tab_pedido_fk (tab_ped_id) references ped_pedido (ped_id) on delete restrict on update cascade	
);

insert into cli_cliente(cli_nome, cli_email, cli_idade)
    values('Erica', 'erica@email.com', 37);
insert into cli_cliente(cli_nome, cli_email, cli_idade)
    values('Erica1', 'erica1@email.com', 37);



insert into ped_pedido(ped_nome, ped_valor)
    values('pedido01', 200);
insert into ped_pedido(ped_nome, ped_valor)
    values('pedido02', 200);

insert into tab_cliente_pedido(tab_cli_id, tab_ped_id)
    values(1,1);
insert into tab_cliente_pedido(tab_cli_id, tab_ped_id)
    values(2,2);