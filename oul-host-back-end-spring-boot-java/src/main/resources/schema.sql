CREATE TABLE IF NOT EXISTS players (
id VARCHAR NOT NULL,
name VARCHAR(50) NOT NULL,
email VARCHAR(30) NOT NULL,
telephone VARCHAR(20) NULL,
player_type INT NOT NULL,
codename VARCHAR(50) NOT NULL,
CONSTRAINT player_id_pk PRIMARY KEY (id),
CONSTRAINT player_name_unique UNIQUE (name),
CONSTRAINT player_email_unique UNIQUE (email)
);