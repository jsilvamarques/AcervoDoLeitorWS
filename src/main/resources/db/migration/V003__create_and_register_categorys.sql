CREATE TABLE category (
	uid BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO category VALUES (1,'Fantasia');
INSERT INTO category VALUES (2,'Ficção');
INSERT INTO category VALUES (3,'Romance');
INSERT INTO category VALUES (4,'Auto-ajuda');
INSERT INTO category VALUES (5,'Literatura Brasileira');
INSERT INTO category VALUES (6,'Terror');
INSERT INTO category VALUES (7,'Suspense');
INSERT INTO category VALUES (8,'Ficção Fantástica');