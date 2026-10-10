CREATE DATABASE IF NOT EXISTS product_db
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

SHOW DATABASES LIKE 'product_db';
use product_db;
SHOW TABLES;
SELECT COUNT(*) FROM products;