CREATE TYPE MediaType AS ENUM ('JPEG', 'JPG', 'PNG');

CREATE TABLE media(
    id          BIGSERIAL      PRIMARY KEY,

    created_at  TIMESTAMP      NOT NULL,
    is_deleted  BOOLEAN        NOT NULL DEFAULT FALSE,

    name        VARCHAR(255)   NOT NULL,
    url         VARCHAR(255)   NOT NULL,
    content     BYTEA          NOT NULL,
    type        MediaType      NOT NULL
);

CREATE UNIQUE INDEX media_name_unq_inx ON media (name);
CREATE UNIQUE INDEX media_url_unq_inx ON media (url);
