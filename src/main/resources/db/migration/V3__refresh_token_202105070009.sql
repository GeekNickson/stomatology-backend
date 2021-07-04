CREATE TABLE app_token
(
    id         bigserial PRIMARY KEY,
    token      varchar(1000) NOT NULL,
    account_id bigint        NOT NULL REFERENCES app_account (id)
);