CREATE TABLE encargos (
   pessoa_codigo BIGINT(20) NOT NULL,
   encargos BIGINT(10) DEFAULT NULL,
  FOREIGN KEY (pessoa_codigo) REFERENCES pessoa(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO encargos VALUES (1, 1), (1, 2), (2, 2), (2, 5);