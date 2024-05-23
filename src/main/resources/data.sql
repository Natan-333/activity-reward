INSERT INTO ACTIVITY_USER (id, name, email, avatar_url, score)
VALUES (99, 'Carolina de Estefano', 'Carolina@fiap.com.br', 'https://i.pravatar.cc/150?img=40', 40);

INSERT INTO ACTIVITY_USER (id, name, email, avatar_url,score)
VALUES (98, 'José da Silva', 'jose@fiap.com.br', 'https://i.pravatar.cc/150?img=59', 30);

INSERT INTO ACTIVITY (title, description, score, status, user_id) 
VALUES ('Teste', 'Criar os testes unitários', 10, 5, 99);

INSERT INTO ACTIVITY (title, description, score, status) 
VALUES ('BD', 'Criar os diagramas do banco', 50, 90);

INSERT INTO ACTIVITY (title, description, score, status) 
VALUES ('Figma', 'Prototipar as telas do app', 70, 80);