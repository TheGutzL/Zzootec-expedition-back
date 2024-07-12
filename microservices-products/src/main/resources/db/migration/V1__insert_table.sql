-- Creación de la tabla 'categories'
CREATE TABLE IF NOT EXISTS categories
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT
);

-- Creación de la tabla 'products'
CREATE TABLE IF NOT EXISTS products
(
    id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
    name               VARCHAR(255) NOT NULL,
    description        TEXT,
    available_quantity DOUBLE,
    price              DECIMAL(19, 2),
    category_id        BIGINT,
    FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE CASCADE
);