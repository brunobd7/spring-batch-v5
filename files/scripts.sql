DROP TABLE IF EXISTS pessoa;
CREATE TABLE pessoa(id INTEGER, nome VARCHAR(500),email VARCHAR(500),data_nascimento TIMESTAMP,idade INTEGER, PRIMARY KEY(id));
DROP TABLE IF EXISTS dados_bancarios;
CREATE TABLE dados_bancarios(id INTEGER, pessoa_id INTEGER,agencia INTEGER, conta INTEGER, banco INTEGER , PRIMARY KEY(id));