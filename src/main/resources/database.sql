INSERT INTO pp313.users (enabled, password, username)
VALUES (true, '$2a$10$OYJj3pfcLsrPKxqoe7/0teaPEMC0pKF3iwUgoHoLcSG9j8YG4asSe', 'admin');

INSERT INTO pp313.users (enabled, password, username)
VALUES (true, '$2a$10$BzNfvxXD8isfdaQ4qMx1n.4FOHOcKdzIdWQ.CuxHR.NsiBQQi8U9m', 'alex');


INSERT INTO pp313.roles (name)
VALUES ('USER');

INSERT INTO pp313.roles (name)
VALUES ('ADMIN');

INSERT INTO pp313.users_roles (user_id, role_id)
VALUES (1, 1);

INSERT INTO pp313.users_roles (user_id, role_id)
VALUES (1, 2);

INSERT INTO pp313.users_roles (user_id, role_id)
VALUES (2, 1);