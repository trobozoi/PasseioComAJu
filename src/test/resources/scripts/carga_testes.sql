CREATE SCHEMA IF NOT EXISTS exemplo AUTHORIZATION sa;

-- Scripts para o Passeio Com a Ju

CREATE TABLE IF NOT EXISTS exemplo.PET (
   id INT NOT NULL GENERATED ALWAYS AS IDENTITY,
   nome VARCHAR(60) NOT NULL,
   cor VARCHAR(60),
   sexo VARCHAR(10),
   especie VARCHAR(60),
   raca VARCHAR(60),
   nascimento DATE NOT NULL,
   peso FLOAT NOT NULL,
   telefone VARCHAR(60),
   endereco_id int,
   PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS exemplo.ENDERECO (
   id INT NOT NULL GENERATED ALWAYS AS IDENTITY,
   cep VARCHAR(255),
   endereco VARCHAR(255),
   numero VARCHAR(255),
   bairro VARCHAR(255),
   cidade VARCHAR(255),
   uf VARCHAR(2),
   PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS exemplo.AGENDA (
   id INT NOT NULL GENERATED ALWAYS AS IDENTITY,
   pet_id int,
   data TIMESTAMP NOT NULL,
   duracao int,
   PRIMARY KEY (id)
);