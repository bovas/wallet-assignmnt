CREATE TABLE PLAYER_ACCOUNT (PLAYER_ID IDENTITY PRIMARY KEY, PLAYER_NAME VARCHAR(255) NOT NULL, CURRENT_BALANCE DOUBLE NOT NULL);
CREATE TABLE TRANSACTION_LOG (ID IDENTITY PRIMARY KEY, TRANSACTION_ID UUID NOT NULL,PLAYER_ID BIGINT NOT NULL, TRANSACTION_STATUS VARCHAR(255) NOT NULL);