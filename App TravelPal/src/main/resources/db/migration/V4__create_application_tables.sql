CREATE TYPE ApplicationStatus AS ENUM ('PENDING', 'ACCEPTED', 'REJECTED');

CREATE TABLE applications(
    id  BIGSERIAL NOT NULL,
    created_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    is_deleted  BOOLEAN   NOT NULL DEFAULT FALSE,
    trip_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    status ApplicationStatus NOT NULL DEFAULT 'PENDING',

    FOREIGN KEY (trip_id)
                REFERENCES trips(id)
                ON DELETE CASCADE,
    FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);