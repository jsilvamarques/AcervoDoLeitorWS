CREATE TABLE publisher (
	uid BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO publisher VALUES (1,'Verus');
INSERT INTO publisher VALUES (2,'Suma de Letras');
INSERT INTO publisher VALUES (3,'Intrínseca');
INSERT INTO publisher VALUES (4,'DarkSide');
INSERT INTO publisher VALUES (5,'Chiado');
INSERT INTO publisher VALUES (6,'Generale/Évora');
INSERT INTO publisher VALUES (7,'Máquina de Escrever');
INSERT INTO publisher VALUES (8,'Amazon');