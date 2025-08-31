CREATE TABLE account
(
    ulid           VARCHAR(12)    NOT NULL,
    user_ulid      VARCHAR(12)    NOT NULL,
    balance        DECIMAL(15, 2) NOT NULL,
    account_number VARCHAR(100)   NOT NULL,
    is_deleted     BIT(1)         NOT NULL,
    created_at     datetime       NOT NULL,
    updated_at     datetime       NOT NULL,
    deleted_at     datetime       NULL,
    CONSTRAINT pk_account PRIMARY KEY (ulid)
);

ALTER TABLE account
    ADD CONSTRAINT uc_account_account_number UNIQUE (account_number);

ALTER TABLE account
    ADD CONSTRAINT FK_ACCOUNT_ON_USER_ULID FOREIGN KEY (user_ulid) REFERENCES user (ulid);
CREATE TABLE user
(
    ulid         VARCHAR(12)  NOT NULL,
    username     VARCHAR(50)  NOT NULL,
    access_token VARCHAR(255) NULL,
    created_at   datetime     NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (ulid)
);

ALTER TABLE user
    ADD CONSTRAINT uc_user_username UNIQUE (username);