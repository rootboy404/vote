CREATE TABLE if not exists session (

    id BIGSERIAL PRIMARY KEY,
    id_associate BIGINT NOT NULL,
    name VARCHAR(50) NOT NULL,
    end_session TIMESTAMPTZ	NOT NULL,

    FOREIGN KEY (id_associate) REFERENCES associate (id)
);
