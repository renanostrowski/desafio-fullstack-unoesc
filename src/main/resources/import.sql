INSERT INTO UNIDADE_FEDERATIVA(CODIGOUF, NOME, SIGLA) VALUES(1, 'Santa Catarina', 'SC');
INSERT INTO UNIDADE_FEDERATIVA(CODIGOUF, NOME, SIGLA) VALUES(2, 'Rio Grande do sul', 'RS');
INSERT INTO UNIDADE_FEDERATIVA(CODIGOUF, NOME, SIGLA) VALUES(2, 'Paraná', 'PR');


INSERT INTO municipio(CODIGO_MUNICIPIO, CODIGOIBGE, NOMEIBGE, CODIGO_REGIAO, PAIS, CODIGOUF) VALUES (1, 4307005, 'Erechim', '2', 'Brasil', 2);

INSERT INTO USUARIO(CODIGO_USUARIO, ADM, EMAIL, NOME, SENHA, CODIGO_MUNICIPIO, ROLE) VALUES (1, true, 'teste@teste.com', 'Teste', '$2a$10$ZqYgiUtTGtgVAWBiwynDDOuW6tfgGfg43cT5o5bIx09bR8AsE5Nsq', 1, 'ADMIN');
INSERT INTO USUARIO(CODIGO_USUARIO, ADM, EMAIL, NOME, SENHA, CODIGO_MUNICIPIO, ROLE) VALUES (2, true, 'admin@admin.com', 'admin', '$2a$10$T61qoNjxDbTJpv7y4dMPnOAGK3BYPZ9Rus7R5T7zi17o6p7UwWsay', 1, 'ADMIN');