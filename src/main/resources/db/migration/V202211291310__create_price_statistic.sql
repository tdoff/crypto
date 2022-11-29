CREATE TABLE IF NOT EXISTS price_statistics (
    id bigint AUTO_INCREMENT PRIMARY KEY,
    crypto_id     bigint     not null,
    timestamp_date timestamp  not null,
    price numeric NOT NULL,
    created_at TIMESTAMP NOT NULL,
    created_by text NOT NULL,
    updated_at TIMESTAMP,
    updated_by text,
    deleted_at TIMESTAMP
);
