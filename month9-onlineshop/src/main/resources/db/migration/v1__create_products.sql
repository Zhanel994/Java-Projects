CREATE TYPE product_category AS ENUM ('SMARTPHONE', 'TABLET', 'LAPTOP');

CREATE TABLE products (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          image_url VARCHAR(255),
                          quantity INT NOT NULL,
                          description TEXT,
                          price NUMERIC NOT NULL,
                          category product_category NOT NULL
);