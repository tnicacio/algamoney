INSERT INTO tb_user (id, first_name, last_name, email, password) VALUES ('277856d9-84a5-437e-8b2d-256ae32cd496'::uuid , 'Alex', 'Brown', 'alex@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (id, first_name, last_name, email, password) VALUES ('1fb33966-cd02-404b-9f4d-4e4f1797cb61'::uuid ,'Maria', 'Green', 'maria@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_role (id, authority) VALUES ('4b5d3243-632c-4460-bb0d-5947e6cdc7f3'::uuid, 'ROLE_OPERATOR');
INSERT INTO tb_role (id, authority) VALUES ('9b3ba9a2-9f3f-4926-ae8e-dc01be610152'::uuid, 'ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES ('277856d9-84a5-437e-8b2d-256ae32cd496'::uuid, '4b5d3243-632c-4460-bb0d-5947e6cdc7f3'::uuid);
INSERT INTO tb_user_role (user_id, role_id) VALUES ('1fb33966-cd02-404b-9f4d-4e4f1797cb61'::uuid, '4b5d3243-632c-4460-bb0d-5947e6cdc7f3'::uuid);
INSERT INTO tb_user_role (user_id, role_id) VALUES ('1fb33966-cd02-404b-9f4d-4e4f1797cb61'::uuid, '9b3ba9a2-9f3f-4926-ae8e-dc01be610152'::uuid);