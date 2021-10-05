CREATE TABLE if not exists vote (

    id BIGSERIAL PRIMARY KEY,
    id_associate BIGINT NOT NULL,
    id_session BIGINT NOT NULL,
    option_vote BOOLEAN NOT NULL,

    FOREIGN KEY (id_associate) REFERENCES associate (id),
    FOREIGN KEY (id_session) REFERENCES session (id)

);