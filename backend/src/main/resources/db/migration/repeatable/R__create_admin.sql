INSERT INTO authorities (created_at, is_deleted, authority)
VALUES
    (CURRENT_TIMESTAMP, FALSE, 'USER'),
    (CURRENT_TIMESTAMP, FALSE, 'ADMIN');

INSERT INTO users (
    created_at,
    is_deleted,
    username,
    password,
    email,
    name
)

VALUES (
                   CURRENT_TIMESTAMP,
                   FALSE,
                   'admin',
                   '$2a$10$l4FOwt1syxqcppHoGJ4PjOCmFK6zk2uGRvsq/1dtizq7OFKmz6ec6', -- пароль = "admin"
                   'admin@gmail.com',
                   'Administrator'
);

INSERT INTO users_authorities (user_id, authorities_id)
VALUES (1, 2);
