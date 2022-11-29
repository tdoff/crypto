alter table price_statistics
    add constraint fk_price_statistics_crypto foreign key (crypto_id)
        references cryptos (id)
        on delete cascade
        on update cascade;