INSERT INTO ACTIVITY_USER (id, name, email, avatar_url)
VALUES (99, 'Carolina de Estefano', 'Carolina@fiap.com.br', 'https://i.pravatar.cc/150?img=40');

INSERT INTO ACTIVITY (title, description, score, status, user_id) 
VALUES ('Teste', 'Criar os testes unitários', 10, 5, 99);

INSERT INTO ACTIVITY (title, description, score, status) 
VALUES ('BD', 'Criar os diagramas do banco', 50, 20);

INSERT INTO ACTIVITY (title, description, score, status) 
VALUES ('Figma', 'Prototipar as telas do app', 15, 45);