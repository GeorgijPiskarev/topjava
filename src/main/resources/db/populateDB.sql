DELETE
FROM meals;
DELETE
FROM user_roles;
DELETE
FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (dateTime, description, calories, user_id)
VALUES (('20-05-20, 12:30:00'), 'user Завтрак', 800, 100000),
       (('20-05-20, 16:40:00'), 'user Обед', 500, 100000),
       (('20-05-20, 20:20:00'), 'user Ужин', 500, 100000),
       (('20-05-20, 00:00:00'), 'user Еда на граничное значение', 300, 100000),
       (('21-05-20, 08:20:00'), 'user Завтрак', 800, 100000),
       (('21-05-20, 12:00:00'), 'user Обед', 1000, 100000),
       (('20-05-20, 09:12:00'), 'admin Завтрак', 600, 100001),
       (('20-05-20, 15:13:00'), 'admin Обед', 500, 100001),
       (('20-05-20, 18:22:00'), 'admin Ужин', 900, 100001);