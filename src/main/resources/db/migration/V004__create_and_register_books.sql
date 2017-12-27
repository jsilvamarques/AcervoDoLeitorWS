CREATE TABLE book (
	isbn VARCHAR(50) PRIMARY KEY NOT NULL,
	name VARCHAR(255) NOT NULL,
  cover VARCHAR(255) NOT NULL,
  thumbnail VARCHAR(255) NOT NULL,
  description LONGTEXT NOT NULL,
  page INT(11),
  release_year INT(4),
  views BIGINT(20) DEFAULT 0,
  creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  publisher_uid BIGINT(20),
  FOREIGN KEY (publisher_uid) REFERENCES publisher(uid)  
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE book_author (
	isbn_book VARCHAR(50) NOT NULL,
	uid_author BIGINT(20) NOT NULL,  
  PRIMARY KEY (isbn_book, uid_author),
  FOREIGN KEY (isbn_book) REFERENCES book(isbn),
  FOREIGN KEY (uid_author) REFERENCES author(uid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE book_category (
	isbn_book VARCHAR(50) NOT NULL,
	uid_category BIGINT(20) NOT NULL,  
  PRIMARY KEY (isbn_book, uid_category),
  FOREIGN KEY (isbn_book) REFERENCES book(isbn),
  FOREIGN KEY (uid_category) REFERENCES category(uid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- inserts book
INSERT INTO book (isbn, name, cover, thumbnail, description, page, release_year, views, creation_date, publisher_uid) 
  VALUES ('9788560280940','It: A Coisa','http://31.220.58.30:8080/acervodoleitorws/livro/imagem/9788560280940_10364170_249161581934790_5361289587341068690_n.jpg','http://31.220.58.30:8080/acervodoleitorws/livro/imagem/thumbnail/thumbnail-9788560280940_10364170_249161581934790_5361289587341068690_n.jpg','Durante as férias escolares de 1958, em Derry, pacata cidadezinha do Maine, Bill, Richie, Stan, Mike, Eddie, Ben e Beverly aprenderam o real sentido da amizade, do amor, da confiança e... do medo. O mais profundo e tenebroso medo. Naquele verão, eles enfrentaram pela primeira vez A Coisa, um ser sobrenatural e maligno que deixou terríveis marcas de sangue em Derry.',1104,'2014',4,'2017-05-26 23:03:27',3);
INSERT INTO book (isbn, name, cover, thumbnail, description, page, release_year, views, creation_date, publisher_uid) 
  VALUES ('9788551000724','Deuses Americanos','http://31.220.58.30:8080/acervodoleitorws/livro/imagem/9788551000724_deuses-americanos-neil-gaiman-acervo-do-leitor-201x300.jpg','http://31.220.58.30:8080/acervodoleitorws/livro/imagem/thumbnail/thumbnail-9788551000724_deuses-americanos-neil-gaiman-acervo-do-leitor-201x300.jpg','Obra-prima de Neil Gaiman, Deuses americanos é relançado pela Intrínseca com conteúdo extra, em Edição Preferida do Autor. Deuses americanos é, acima de tudo, um livro estranho. E foi essa estranheza que tornou o romance de Neil Gaiman, publicado pela primeira vez em 2001, um clássico imediato. Nesta nova edição, preferida do autor, o leitor encontrará capítulos revistos e ampliados, artigos, uma entrevista com Gaiman e um inspirado texto de introdução. ',576,'2016',0,'2017-05-26 23:07:05',3);
INSERT INTO book (isbn, name, cover, thumbnail, description, page, release_year, views, creation_date, publisher_uid) 
  VALUES ('9788566636796','Abominação','http://31.220.58.30:8080/acervodoleitorws/livro/imagem/9788566636796_capa-abominacao-darkside-acervo-do-leitor-209x300.jpg','http://31.220.58.30:8080/acervodoleitorws/livro/imagem/thumbnail/thumbnail-9788566636796_capa-abominacao-darkside-acervo-do-leitor-209x300.jpg','O Primeiro Romance De Gary Whitta, Também Autor Do Aclamado Star Wars: Rogue One, É Uma Aventura Para Os Leitores Mais Valentes.?Whitta Transforma O Gore Em Momentos De Grande Beleza. Abominação É Uma Mistura Épica Entre Fantasia Histórica, Ficção Científica E A Magia Da Cultura Nórdica.',320,'2016',0,'2017-05-26 23:24:47',4);
INSERT INTO book (isbn, name, cover, thumbnail, description, page, release_year, views, creation_date, publisher_uid) 
  VALUES ('9788581053103','Revival','http://31.220.58.30:8080/acervodoleitorws/livro/imagem/9788581053103_capa-revival-stephen-king-acervo-do-leitor-199x300.jpg','http://31.220.58.30:8080/acervodoleitorws/livro/imagem/thumbnail/thumbnail-9788581053103_capa-revival-stephen-king-acervo-do-leitor-199x300.jpg','Em uma cidadezinha na Nova Inglaterra (EUA), mais de meio século atrás, uma sombra recai sobre um menino que brinca com seus soldadinhos de plástico no quintal. Jamie Morton olha para o alto e vê a figura impressionante do novo pastor. O reverendo Charles Jacobs, junto com a bela esposa e o filho, chegam para reacender a fé local. Homens e meninos, mulheres e garotas, todos ficam encantados pela família perfeita e os sermões contagiantes. Jamie e o reverendo passam a compartilhar um elo ainda mais forte, baseado em uma obsessão secreta. Até que uma desgraça atinge Jacobs e o faz ser banido da cidade. Décadas depois, Jamie carrega seus próprios demônios. Integrante de uma banda que vive na estrada, ele leva uma vida nômade no mais puro estilo sexo, drogas e rock and roll, fugindo da própria tragédia familiar. Agora, com trinta e poucos anos, viciado em heroína, perdido, desesperado, Jamie reencontra o antigo pastor. O elo que os unia se transforma em um pacto que assustaria até o diabo, com sérias consequências para os dois, e Jamie percebe que \"reviver\" pode adquirir vários significados.',376,'2015',0,'2017-06-11 21:08:47',2);
INSERT INTO book (isbn, name, cover, thumbnail, description, page, release_year, views, creation_date, publisher_uid) 
  VALUES ('9789895170463','Corações nas Sombras – Vol 1 : Presságios de Guerra','http://31.220.58.30:8080/acervodoleitorws/livro/imagem/9789895170463_capa-coracoes-nas-sombras-vol-1-pressagios-de-guerra-allan-francis212x300.jpg','http://31.220.58.30:8080/acervodoleitorws/livro/imagem/thumbnail/thumbnail-9789895170463_capa-coracoes-nas-sombras-vol-1-pressagios-de-guerra-allan-francis212x300.jpg','Quando eu olhei através do passado eu finalmente compreendi o que você entenderá aos poucos. Ver a queda e extinção dos centauros por sua sede de poder foi apenas o estopim de algo maior, pois o mal que despertaram no mundo inferior (Agonia) embora selado por Círdan o elfo, desencadeou uma série de acontecimentos que narro para ti. Aquilo bastou para que Goldax o imortal que liderou os orcs por duzentos anos encontrasse um mestre que lhe prometeu libertar os orcs de seu exílio. Depois de sua derrocada, o dragão negro ressurgiu havido por poder e adoração, a ponto do rei dos dragões lhe temer. A Casa de Prata com intenções desconhecidas começou a roubar um a um os talismãs de Ifíanor. O mundo aos poucos começou a odiar os magos seus antigos benfeitores e uma mente brilhante surgiu com a finalidade de equilibrar as coisas, mas ele não sabia que seus atos acarretariam uma guerra sem fim. Então meu amor, meu confidente e meu amante, se lhe conto sobre o passado é para que você entenda o meu papel no presente e o porquê de termos nos separados. Escolhi nomear este relato de Corações nas Sombras e acredito que você entenderá o motivo.',736,'2016',0,'2017-06-11 21:08:47',5);
INSERT INTO book (isbn, name, cover, thumbnail, description, page, release_year, views, creation_date, publisher_uid) 
  VALUES ('9788563993724','Diário de um exorcista','http://31.220.58.30:8080/acervodoleitorws/livro/imagem/9788563993724_CAPA-DIARIO-DE-UM-EXORCISTA-ACERVO-DO-LEITOR-206x300.jpg','http://31.220.58.30:8080/acervodoleitorws/livro/imagem/thumbnail/thumbnail-9788563993724_CAPA-DIARIO-DE-UM-EXORCISTA-ACERVO-DO-LEITOR-206x300.jpg','Desde muito jovem, Lucas é atormentado por inimigos sobrenaturais cruéis e impiedosos. Quando uma tragédia familiar inexplicável abala sua família, o menino desperta para a mais importante e desafiadora missão que um ser humano pode enfrentar, uma luta sem fim contra o inimigo maior do homem e de Deus: o próprio Diabo.',256,'2013',0,'2017-06-11 21:08:47',6);
INSERT INTO book (isbn, name, cover, thumbnail, description, page, release_year, views, creation_date, publisher_uid) 
  VALUES ('B01M0D1XT6','A canção dos shenlongs: Guerras Épicas do Império de Housai','http://31.220.58.30:8080/acervodoleitorws/livro/imagem/B01M0D1XT6_CAPA-A-CANCAO-DOS-SHENLONGS-ACERVO-DO-LEITOR-211x300.jpg','http://31.220.58.30:8080/acervodoleitorws/livro/imagem/thumbnail/thumbnail-B01M0D1XT6_CAPA-A-CANCAO-DOS-SHENLONGS-ACERVO-DO-LEITOR-211x300.jpg','Os tempos mudaram. A ascensão do Império de Housai obrigou os monges guerreiros shenlongs a se isolarem cada vez mais. Com o passar dos anos, os Quatro Templos sagrados se tornaram seu último refúgio. Os Antigos se foram. Seus descendentes desapareceram. Aqueles que resistem à nova ordem estão enfraquecidos.',83,'2016',0,'2017-06-11 21:08:47',8);
INSERT INTO book (isbn, name, cover, thumbnail, description, page, release_year, views, creation_date, publisher_uid) 
  VALUES ('9788565269377','Febre Vermelha','http://31.220.58.30:8080/acervodoleitorws/livro/imagem/9788565269377_CAPA-FEBRE-VERMELHA-ACERVO-DO-LEITOR-216x300.jpg','http://31.220.58.30:8080/acervodoleitorws/livro/imagem/thumbnail/thumbnail-9788565269377_CAPA-FEBRE-VERMELHA-ACERVO-DO-LEITOR-216x300.jpg','Um navio desgovernado encalha nas pedras em Praia Grande, com sua tripulação brutalmente assassinada em alto mar. Em pleno verão na Baixada Santista, a manchete nos jornais é vista com indiferença pela população, que está mais preocupada em curtir o feriado de ano novo. Em poucos dias, uma epidemia misteriosa se espalha pelo litoral, deixando seus infectados com uma febre ensandecedora, olhos vermelhos e fome insaciável. Ocorrências de extrema violência e canibalismo tornam-se cada vez mais comuns, e as autoridades não são capazes de lidar com o caos que domina as ruas e ameaça contagiar todo o país.',286,'2016',0,'2017-06-11 21:08:47',7);
INSERT INTO book (isbn, name, cover, thumbnail, description, page, release_year, views, creation_date, publisher_uid) 
  VALUES ('9788581050454','Salem','http://31.220.58.30:8080/acervodoleitorws/livro/imagem/9788581050454_Salem.jpg','http://31.220.58.30:8080/acervodoleitorws/livro/imagem/thumbnail/thumbnail-9788581050454_Salem.jpg','Publicado originalmente em 1975, A Hora do Vampiro é inspirado em o Drácula de Bram Stoker. Segundo livro da carreira de King, a obra deu origem ao filme Os Vampiros de Salem, dirigido por Tobe Hopper, de O Massacre da Serra Elétrica.Ambientado na cidadezinha de Jerusalem\'s Lot, na Nova Inglaterra, o romance conta a história de três forasteiros. Ben Mears, um escritor que viveu alguns anos na cidade quando criança e está disposto a acertar contas com o próprio passado; Mark Petrie, um menino obcecado por monstros e filmes de terror; e o Senhor Barlow, uma figura misteriosa que decide abrir uma loja na cidade.',464,'2013',3,'2017-08-09 18:50:27',2);

-- inserts books_author
INSERT INTO book_author (isbn_book, uid_author) VALUES ('9788560280940',1);
INSERT INTO book_author (isbn_book, uid_author) VALUES ('9788551000724',2);
INSERT INTO book_author (isbn_book, uid_author) VALUES ('9788566636796',3);
INSERT INTO book_author (isbn_book, uid_author) VALUES ('9788581053103',1);
INSERT INTO book_author (isbn_book, uid_author) VALUES ('9788563993724',6);
INSERT INTO book_author (isbn_book, uid_author) VALUES ('9788563993724',7);
INSERT INTO book_author (isbn_book, uid_author) VALUES ('B01M0D1XT6',8);
INSERT INTO book_author (isbn_book, uid_author) VALUES ('9788565269377',9);
INSERT INTO book_author (isbn_book, uid_author) VALUES ('9789895170463',5);
INSERT INTO book_author (isbn_book, uid_author) VALUES ('9788581050454',1);

-- inserts books_categoryes
INSERT INTO book_category (isbn_book, uid_category) VALUES ('9788560280940',6);
INSERT INTO book_category (isbn_book, uid_category) VALUES ('9788560280940',7);
INSERT INTO book_category (isbn_book, uid_category) VALUES ('9788560280940',8);
INSERT INTO book_category (isbn_book, uid_category) VALUES ('9788551000724',1);
INSERT INTO book_category (isbn_book, uid_category) VALUES ('9788551000724',8);
INSERT INTO book_category (isbn_book, uid_category) VALUES ('9788566636796',6);
INSERT INTO book_category (isbn_book, uid_category) VALUES ('9788566636796',7);
INSERT INTO book_category (isbn_book, uid_category) VALUES ('9788566636796',8);
INSERT INTO book_category (isbn_book, uid_category) VALUES ('9788581053103',7);
INSERT INTO book_category (isbn_book, uid_category) VALUES ('9789895170463',7);
INSERT INTO book_category (isbn_book, uid_category) VALUES ('9788563993724',7);
INSERT INTO book_category (isbn_book, uid_category) VALUES ('B01M0D1XT6',1);
INSERT INTO book_category (isbn_book, uid_category) VALUES ('9788565269377',7);
INSERT INTO book_category (isbn_book, uid_category) VALUES ('9788581050454',6);



  
  