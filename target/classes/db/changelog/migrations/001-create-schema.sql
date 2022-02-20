CREATE TABLE singer
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(256)
);

CREATE TABLE recording
(
    id           BIGSERIAL PRIMARY KEY,
    song_code    VARCHAR(32),
    title        VARCHAR(4096),
    version      VARCHAR(128),
    release_time TIMESTAMP,
    singer_id    BIGINT REFERENCES singer (id)
);

CREATE TABLE company
(
    id           BIGSERIAL PRIMARY KEY,
    name         VARCHAR(256)
);

CREATE TABLE song_rights
(
    id              BIGSERIAL PRIMARY KEY,
    recording_id    BIGINT REFERENCES recording (id),
    company_id      BIGINT REFERENCES company (id),
    price           FLOAT
);
