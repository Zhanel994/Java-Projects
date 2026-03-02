CREATE TABLE authorities(
    id          BIGSERIAL PRIMARY KEY,
    created_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    is_deleted  BOOLEAN   NOT NULL DEFAULT FALSE,
    authority   VARCHAR(15) NOT NULL
);

CREATE TABLE users(
    id          BIGSERIAL PRIMARY KEY,
    created_at  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    is_deleted  BOOLEAN     NOT NULL DEFAULT FALSE,

    username    VARCHAR(25) NOT NULL UNIQUE,
    password    VARCHAR     NOT NULL,
    email       VARCHAR     NOT NULL UNIQUE,

    name        VARCHAR
);

CREATE TABLE users_authorities(
    id              BIGSERIAL PRIMARY KEY,
    user_id         BIGINT NOT NULL,
    authorities_id  BIGINT NOT NULL,

    FOREIGN KEY (user_id)
        REFERENCES users (id)
        ON DELETE CASCADE,

    FOREIGN KEY (authorities_id)
        REFERENCES authorities (id)
        ON DELETE CASCADE
);
