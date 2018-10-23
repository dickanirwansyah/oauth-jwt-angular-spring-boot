INSERT INTO privileges (privileges_id, name, description) VALUES (1, 'PRIVILEGE_ADMIN_READ', 'description for privilege admin read');
INSERT INTO privileges (privileges_id, name, description) VALUES (2, 'PRIVILEGE_USER_READ', 'description for privilege user read');

INSERT INTO roles (roles_id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles (roles_id, name) VALUES (2, 'ROLE_USER');

INSERT INTO users(email, username, enable, firstname, lastname, password)
VALUES ('admin@gmail.com', 'admin', true, 'admin', 'admin', '$2a$10$MTFVrdqbHOi.CCUhkrkZnOBdrZEfk3gzIUyZBdQvLWvdF/0pnkEO2');

INSERT INTO users(email, username, enable, firstname, lastname, password)
VALUES ('user@gmail.com', 'user', true, 'user', 'user', '$2a$10$6KDklkImZgGANWR8pDAwSexf6Bt4Z9I0nDiwdih9Q38HI4eAkWk0u');

INSERT INTO users_roles (users_user_id, roles_roles_id) VALUES (1, 1);
INSERT INTO users_roles (users_user_id, roles_roles_id) VALUES (2, 2);