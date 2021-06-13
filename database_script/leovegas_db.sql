
CREATE TABLE public.wallet
(
    wallet_id bigint NOT null,
    balance double,
    created_at timestamp DEFAULT current_timestamp,
    currency_code varchar(255),
    CONSTRAINT wallet_pkey PRIMARY KEY (wallet_id)
);

CREATE TABLE public.player
(
    player_id bigint NOT NULL,
    player_name varchar(255),
    wallet_id bigint,
    CONSTRAINT player_pkey PRIMARY KEY (player_id),
    CONSTRAINT fk_wallet FOREIGN KEY (wallet_id)
        REFERENCES public.wallet (wallet_id)
);


CREATE TABLE public.transactions
(
    transaction_id varchar(255),
    amount bigint,
    currency_code varchar(255),
    transaction_date  timestamp DEFAULT current_timestamp,
    transaction_type varchar(255),
    player_id bigint,
    CONSTRAINT transactions_pkey PRIMARY KEY (transaction_id),
    CONSTRAINT player_fk FOREIGN KEY (player_id)
        REFERENCES public.player (player_id)
);
