CREATE SCHEMA IF NOT EXISTS exemplo AUTHORIZATION sa;

-- Scripts para o myBank

DROP TABLE IF EXISTS exemplo.LANCAMENTO;

CREATE TABLE IF NOT EXISTS exemplo.LANCAMENTO (
   codigoLancamento INT NOT NULL GENERATED ALWAYS AS IDENTITY,
   codigoCliente INT NOT NULL,
   dataLancamento DATE NOT NULL,
   valorLancamento FLOAT NOT NULL,
   PRIMARY KEY (codigoLancamento)
);

DROP TABLE IF EXISTS exemplo.CONTA;

CREATE TABLE IF NOT EXISTS exemplo.CONTA (
   codigoConta INT NOT NULL,
   nomeCliente VARCHAR(60) NOT NULL,
   limiteConta INT NOT NULL,
   estadoConta BOOLEAN NOT NULL,
   estadoLimite BOOLEAN NOT NULL,
   PRIMARY KEY (codigoConta)
);

DROP TABLE IF EXISTS exemplo.SOLICITACAO;

CREATE TABLE IF NOT EXISTS exemplo.SOLICITACAO (
   numeroSolicitacao INT NOT NULL GENERATED ALWAYS AS IDENTITY,
   solicitacaoPendente BOOLEAN NOT NULL,
   tipoSolicitacao INT NOT NULL,
   valorSolicitacao INT NOT NULL,
   contaSolicitacao INT NOT NULL,
   PRIMARY KEY (numeroSolicitacao)
);

--Essa inserção é essencial para o teste de lançamento.
INSERT INTO exemplo.LANCAMENTO (codigoLancamento, codigoCliente, dataLancamento, valorLancamento) VALUES (1, 1234567, '2000-09-20', 1200);
INSERT INTO exemplo.CONTA (codigoConta, nomeCliente, limiteConta, estadoConta, estadoLimite) VALUES (1234567, 'Cliente com Limite', 100, true, true);
INSERT INTO exemplo.CONTA (codigoConta, nomeCliente, limiteConta, estadoConta, estadoLimite) VALUES (1234568, 'Cliente com Limite 0', 0, true, true);
INSERT INTO exemplo.CONTA (codigoConta, nomeCliente, limiteConta, estadoConta, estadoLimite) VALUES (1234569, 'Cliente sem Limite', 0, true, false);
INSERT INTO exemplo.CONTA (codigoConta, nomeCliente, limiteConta, estadoConta, estadoLimite) VALUES (1234570, 'Cliente sem Limite 100', 100, true, false);

INSERT INTO exemplo.CONTA (codigoConta, nomeCliente, limiteConta, estadoConta, estadoLimite) VALUES (101, 'Cliente', 0, false, false);
INSERT INTO exemplo.CONTA (codigoConta, nomeCliente, limiteConta, estadoConta, estadoLimite) VALUES (102, 'Cliente', 0, false, false);


INSERT INTO exemplo.CONTA (codigoConta, nomeCliente, limiteConta, estadoConta, estadoLimite) VALUES (200, 'Cliente', 0, true, true);
INSERT INTO exemplo.LANCAMENTO (codigoLancamento, codigoCliente, dataLancamento, valorLancamento) VALUES (200, 200, '2000-09-20', 1200);

INSERT INTO exemplo.CONTA (codigoConta, nomeCliente, limiteConta, estadoConta, estadoLimite) VALUES (201, 'wilson maria da silva de alencar', 0, true, true);
INSERT INTO exemplo.LANCAMENTO (codigoLancamento, codigoCliente, dataLancamento, valorLancamento) VALUES (201, 201, '2000-09-20', 1200);

INSERT INTO exemplo.CONTA (codigoConta, nomeCliente, limiteConta, estadoConta, estadoLimite) VALUES (202, 'wilson maria da silva de alencar', 0, true, true);
INSERT INTO exemplo.LANCAMENTO (codigoLancamento, codigoCliente, dataLancamento, valorLancamento) VALUES (202, 202, '2000-09-20', 100);

INSERT INTO exemplo.CONTA (codigoConta, nomeCliente, limiteConta, estadoConta, estadoLimite) VALUES (203, 'wilson maria da silva de alencar', 0, true, true);
INSERT INTO exemplo.LANCAMENTO (codigoLancamento, codigoCliente, dataLancamento, valorLancamento) VALUES (203, 203, '2000-09-20', 100);


insert into exemplo.CONTA (codigoConta, nomeCliente, limiteConta, estadoConta, estadoLimite) values (0, 'Cliente 00',   0, false, false);
insert into exemplo.CONTA (codigoConta, nomeCliente, limiteConta, estadoConta, estadoLimite) values (1, 'Cliente 01', 100, false, true);
insert into exemplo.CONTA (codigoConta, nomeCliente, limiteConta, estadoConta, estadoLimite) values (2, 'Cliente 02', 200,  true, false);
insert into exemplo.CONTA (codigoConta, nomeCliente, limiteConta, estadoConta, estadoLimite) values (3, 'Cliente 03', 300,  true, true);
insert into exemplo.CONTA (codigoConta, nomeCliente, limiteConta, estadoConta, estadoLimite) values (4, 'Cliente 04', 400,  true, true);
insert into exemplo.CONTA (codigoConta, nomeCliente, limiteConta, estadoConta, estadoLimite) values (5, 'Cliente 05', 500,  true, true);
insert into exemplo.CONTA (codigoConta, nomeCliente, limiteConta, estadoConta, estadoLimite) values (6, 'Cliente 06', 600, false, false);

insert into exemplo.LANCAMENTO (codigoCliente, dataLancamento, valorLancamento) values (2, getdate(), 200);
insert into exemplo.LANCAMENTO (codigoCliente, dataLancamento, valorLancamento) values (3, getdate(), 300);
insert into exemplo.LANCAMENTO (codigoCliente, dataLancamento, valorLancamento) values (4, getdate(), 400);
insert into exemplo.LANCAMENTO (codigoCliente, dataLancamento, valorLancamento) values (5, getdate(), 500);

delete from exemplo.SOLICITACAO;

insert into exemplo.SOLICITACAO (solicitacaoPendente, tipoSolicitacao, valorSolicitacao, contaSolicitacao) values (true, 0, 5000, 5);
insert into exemplo.SOLICITACAO (solicitacaoPendente, tipoSolicitacao, valorSolicitacao, contaSolicitacao) values (true, 1, 3000, 3);
