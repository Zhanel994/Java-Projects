CREATE TABLE trips(
    id  BIGSERIAL PRIMARY KEY,
    created_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    is_deleted  BOOLEAN   NOT NULL DEFAULT FALSE,
    title VARCHAR NOT NULL,
    city VARCHAR NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    max_participants INT NOT NULL,
    description VARCHAR NOT NULL,
    media_id BIGINT NOT NULL,
    creator_id BIGINT NOT NULL,

    FOREIGN KEY (media_id)
                  REFERENCES media(id),
    FOREIGN KEY (creator_id)
                  REFERENCES users(id)
);

CREATE TABLE trip_participants(
    trip_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,

    PRIMARY KEY (trip_id, user_id),
    FOREIGN KEY (trip_id)
                REFERENCES trips(id)
                ON DELETE CASCADE,
    FOREIGN KEY (user_id)
                REFERENCES users(id)
                ON DELETE CASCADE
);