CREATE TABLE profile (
	uid VARCHAR(50) PRIMARY KEY NOT NULL,
  name VARCHAR(50) NOT NULL,
  lastname VARCHAR(50),
  photo VARCHAR(255),
  thumbnail VARCHAR(255),
  status VARCHAR(50),
  score BIGINT(20),  
  creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO profile (uid, name, lastname, photo, status, score) 
  VALUES ('bgzh0OS5IegSvK3wmoUASk5xhL92','Jefferson','Marques','http://31.220.58.30:8080/acervodoleitorws/perfil/imagem/profile_bgzh0OS5IegSvK3wmoUASk5xhL92','Pendente',0);
