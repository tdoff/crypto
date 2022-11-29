CREATE TABLE IF NOT EXISTS cryptos(
    id bigint AUTO_INCREMENT PRIMARY KEY,
    name text NOT NULL
    created_at TIMESTAMP NOT NULL,
    created_by text NOT NULL,
    updated_by text,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP
);
