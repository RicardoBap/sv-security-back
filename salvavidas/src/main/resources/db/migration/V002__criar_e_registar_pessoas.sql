CREATE TABLE pessoa (
	codigo BIGINT PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	senha VARCHAR(150) NOT NULL,
	telefone VARCHAR(15) NOT NULL,
	email VARCHAR(30) NOT NULL UNIQUE,	
	ativo BOOLEAN NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO pessoa (nome, senha, telefone, email, ativo) 
VALUES ('Ricardo', '$2a$10$XNkP0arhNhleuKGGqqLmJuQ4Tsz5c4w8z9xJMfEraevadk5rM2h7O', '(19) 99659-2800', 'ric_bap@hotmail.com', true);

INSERT INTO pessoa (nome, senha, telefone, email, ativo) 
VALUES ('Maria Silva', '$2a$10$8Os2gleRHcKCh7PEfInQTOjFhO.NF6aU4rk5LFrTEDb3ngQ8NlYwi', '(19) 3269-2161', 'maria@hotmail.com', true);
