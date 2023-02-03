
-- -----------------------------------------------------
--                       Tabelas
-- -----------------------------------------------------
CREATE TABLE usuario (
  us_codUsuario serial NOT NULL, 
  us_nome VARCHAR(100) NULL,
  us_telefone VARCHAR(14) NULL,
  us_email VARCHAR(30) NULL,
  us_usuario VARCHAR(15) NULL,
  us_senha VARCHAR(10) NULL,
  us_status VARCHAR(1) NULL,
  PRIMARY KEY (us_codUsuario));



CREATE TABLE caixa (
  cx_codCaixa serial NOT NULL,
  usuario_us_codUsuario INT NOT NULL,
  cx_vl_abertura FLOAT NULL,
  cx_data_abertura DATE NULL,
  cx_hora_abertura DATE NULL,
  cx_vl_fechamento FLOAT NULL,
  cx_data_fechamento DATE NULL,
  cx_hora_fechamento DATE NULL,
  PRIMARY KEY (cx_codCaixa),
    FOREIGN KEY (usuario_us_codUsuario)
    REFERENCES usuario (us_codUsuario));

CREATE TABLE cliente (
  cli_codCli serial NOT NULL,
  cli_tipo VARCHAR(10) NULL,
  cli_nome VARCHAR(100) NULL,
  cli_cpf VARCHAR(14) NULL,
  cli_cpnj VARCHAR(14) NULL,
  cli_data_nasc DATE NULL DEFAULT NULL,
  cli_telefone VARCHAR(14) NULL DEFAULT NULL,
  cli_limite FLOAT NULL,
  cli_email VARCHAR(30) NULL DEFAULT NULL,
  cli_logradouro VARCHAR(40) NULL,
  cli_numero VARCHAR(6) NULL,
  cli_bairro VARCHAR(30) NULL,
  cli_cidade VARCHAR(30) NULL,
  cli_estado VARCHAR(2) NULL,
  cli_cep VARCHAR(9) NULL,
  cli_status VARCHAR(1) NOT NULL
  PRIMARY KEY (cli_codCli));

CREATE TABLE venda (
  vnd_codVend serial  NOT NULL,
  Cliente_cli_codCli INT NOT NULL,
  usuario_us_codUsuario INT NOT NULL,
  vnd_data DATE NULL,
  vnd_valor FLOAT NULL,
  vnd_formapgto  NULL,
  vnd_desconto FLOAT NULL DEFAULT NULL,
  vnd_status VARCHAR(1) NULL,
  vnd_statuspagamento VARCHAR(1) NULL,
  PRIMARY KEY (vnd_codVend),
    FOREIGN KEY (Cliente_cli_codCli)
    REFERENCES cliente (cli_codCli),
    FOREIGN KEY (usuario_us_codUsuario)
    REFERENCES usuario (us_codUsuario));
   
CREATE TABLE pagamento (
  codPagamento SERIAL NOT NULL,
  Caixa_cx_codCaixa INT NOT NULL,
  venda_vnd_codVend INT NOT NULL,
  pag_valorPago FLOAT NULL,
  pag_data DATE NULL,
  pag_meiopagamento VARCHAR(20) NOT NULL,
  PRIMARY KEY (codPagamento),
    FOREIGN KEY (Caixa_cx_codCaixa)
    REFERENCES caixa (cx_codCaixa),
    FOREIGN KEY (venda_vnd_codVend)
    REFERENCES venda (vnd_codVend));

CREATE TABLE tipodespesa (
  tpdesp_cod serial NOT NULL,
  tpdesp_descricao VARCHAR(25) NULL,
  tpdesp_status VARCHAR(1) NULL,
  PRIMARY KEY (tpdesp_cod));

CREATE TABLE despesa (
  desp_codDesp serial NOT NULL,
  TipoDespesa_tpdesp_cod INT NOT NULL,
  usuario_us_codUsuario INT NOT NULL,
  caixa_cx_codCaixa INT NOT NULL,
  desp_valor FLOAT NULL,
  desp_data_vencimento DATE NULL,
  desp_data_pagamento DATE NULL,
  desp_vl_pago FLOAT NULL,
  desp_descricao VARCHAR(60) NOT NULL,
  desp_status VARCHAR(1) NULL,
  desp_statusPagamento VARCHAR(1) NULL,
  PRIMARY KEY (desp_codDesp),
    FOREIGN KEY (TipoDespesa_tpdesp_cod)
    REFERENCES tipodespesa (tpdesp_cod),
    FOREIGN KEY (usuario_us_codUsuario)
    REFERENCES usuario (us_codUsuario),
    FOREIGN KEY (caixa_cx_codCaixa)
    REFERENCES caixa (cx_codCaixa));

CREATE TABLE produto (
  prod_codProd serial NOT NULL,
  prod_descricao VARCHAR(200) NULL,
  prod_preco FLOAT NULL,
  prod_status VARCHAR(1) NULL,
  PRIMARY KEY (prod_codProd));

CREATE TABLE itemvenda (
  Venda_vnd_codVend SERIAL NOT NULL,
  Produto_prod_codProd INT NOT NULL,
  itv_qtd INT NULL,
  itv_valor_uni FLOAT NULL,
  PRIMARY KEY (Venda_vnd_codVend, Produto_prod_codProd),
    FOREIGN KEY (Venda_vnd_codVend)
    REFERENCES venda (vnd_codVend),
    FOREIGN KEY (Produto_prod_codProd)
    REFERENCES produto (prod_codProd));





----- Histórico de ações e comandos: ------

--DROP DATABASE fb_comercio_gelo2;
--CREATE DATABASE fb_comercio_gelo2
--drop database fb_comercio_gelo2;
--create database fb_comercio_gelo;
--use fb_comercio_gelo;	
--select * from despesa;
--alter table despesa alter column desp_codDesp TYPE serial;
--alter table tipodespesa alter column tpdesp_cod TYPE serial;
--modify table tipodespesa alter column tpdesp_cod TYPE serial;


--alter table despesa alter column desp_codDesp TYPE serial;
--alter table pagamento alter column codPagamento TYPE serial;



select * from usuario;
select * from produto;

SELECT * FROM Produto p WHERE p.prod_status = 'D' order by p.prod_descricao ASC;

select * from caixa;

select * from ItemVenda;

describe pagamento;

select* from itemvenda;
select * from venda

--insert into pagamento (caixa_cx_codcaixa, venda_vnd_codvend, pag_valorpago, pag_data, pag_meiopagamento) values (2, 107, 100, '2002-03-22', 'Dinheiro');

/*delete from venda where vnd_codVend > 0;

delete from ItemVenda where Venda_vnd_codVend > 0;*/



alter table despesa add column  desp_statusPagamento VARCHAR(1) NULL;

 select   pagamento.pag_valorpago  despesa.desp_vl_pago WHERE  despesa.caixa_cx_codcaixa = pagamento.caixa_cx_codcaixa ;


 select pagamento.codpagamento,  pagamento.venda_vnd_codvend , pagamento.caixa_cx_codcaixa,  cliente.cli_nome,  pagamento.pag_meiopagamento,
   pagamento.pag_data,  pagamento.pag_valorpago from Pagamento, Cliente, Venda 
   where  pagamento.venda_vnd_codvend = venda.vnd_codvend and  venda.cliente_cli_codcli = cliente.cli_codcli; 

select  pagamento.pag_valorpago,  despesa.desp_vl_pago,
(select SUM(p.pag_valorpago) from pagamento p) as resultadoPagamento,
(select SUM(d.desp_vl_pago) from despesa d) as resultadoDespesa,
(SUM(pagamento.pag_valorpago) - SUM(despesa.desp_vl_pago)) as resultadoTotal
 from Pagamento, Despesa where   despesa.caixa_cx_codcaixa =  pagamento.caixa_cx_codcaixa
  group by  pagamento.pag_valorpago, despesa.desp_vl_pago;

select * from caixa;

select sum(desp_valor) from despesa;

select sum(pag_valorPago) from pagamento;

alter table despesa add column desp_valor FLOAT;

select * from despesa;

select  pagamento.pag_valorpago,  despesa.desp_vl_pago, TO_CHAR(caixa.cx_data_abertura, 'DD/MM/YYYY') 
 , caixa.cx_vl_abertura ,SUM(pagamento.pag_valorpago)  as resultadoPagamento, SUM(despesa.desp_vl_pago) as resultadoDespesa,
(SUM(pagamento.pag_valorpago) - SUM(despesa.desp_vl_pago)+caixa.cx_vl_abertura) as resultadoTotal
 from Pagamento, Despesa, Caixa where   despesa.caixa_cx_codcaixa =  pagamento.caixa_cx_codcaixa and 
  caixa.cx_data_abertura='27-06-2022'/* and pagamento.caixa_cx_codcaixa=7*/
  group by  pagamento.pag_valorpago, despesa.desp_vl_pago,  caixa.cx_data_abertura,  caixa.cx_vl_abertura;

select * from cliente;
select *from caixa;
select * from pagamento;
select sum(pag_valorpago) from Pagamento;
delete pagamento where 
select * from despesa;
select * from venda;

select * from cliente;
select * from usuario;
select * from itemvenda;
select * from tipodespesa;
select* from caixa;

delete 


--alter table venda alter column vnd_formapgto TYPE VARCHAR(10);

alter table despesa add column  desp_statusPagamento VARCHAR(1) NULL;
alter table tipodespesa add column tpdesp_status  VARCHAR(1) NULL; 
SELECT td FROM TipoDespesa td WHERE td.tpdesp_status = "A";

select * from venda
--alter unique
/*
ALTER TABLE equipment 
ADD CONSTRAINT unique_equip_id 
UNIQUE USING INDEX equipment_equip_id;*/

--alter table usuario add constraint unique_

