INSERT INTO user (firstname, lastname, password, pseudo, role, email ) VALUES
                                                                           ('eric', 'lanza', '123456', 'ricou', 'ROLE_ADMIN', 'lanzae32@gmail.com'),
                                                                           ('toto', 'roux', '123456', 'toto', 'ROLE_USER', 'toto@gmail.com');

INSERT INTO edibility (id,name) VALUES
                                    (1,'Mortel'),
                                    (2,'Toxique'),
                                    (3,'A rejeter'),
                                    (4,'Comestible médiocre'),
                                    (5,'Bon comestible'),
                                    (6,'Excellent comestible');

INSERT INTO mushroom (id, visibility, created_at, updated_at, latinname, commonname, hat, typelameschamp, lamella, foot, flesh, habitat, comment, id_edibility, id_user) VALUES
(1, 1, '2020-03-20 23:00:00','2020-03-20 23:00:00', 'BOLETUS EDULIS', 'Cèpe de Bordeaux', 'z', 'd', 't', 'test', NULL, ' ', 6, 1);