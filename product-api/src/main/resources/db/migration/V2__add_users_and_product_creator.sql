CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_users_email UNIQUE (email)
);

ALTER TABLE products
    ADD COLUMN created_by BIGINT NOT NULL,
    ADD CONSTRAINT fk_products_created_by
        FOREIGN KEY (created_by) REFERENCES users(id);