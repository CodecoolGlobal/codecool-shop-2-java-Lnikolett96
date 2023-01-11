DROP TABLE IF EXISTS products CASCADE;
DROP TABLE IF EXISTS suppliers CASCADE ;
DROP TABLE IF EXISTS product_categories CASCADE;
DROP TABLE IF EXISTS order_header CASCADE ;
DROP TABLE IF EXISTS order_items CASCADE;

CREATE TABLE products (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR,
    price NUMERIC,
    category_id INTEGER,
    supplier_id INTEGER,
    currency VARCHAR,
    description VARCHAR,
    image_file_name VARCHAR
);

CREATE TABLE suppliers (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR,
    description VARCHAR
);

ALTER TABLE ONLY products
    ADD CONSTRAINT fk_supplier_id FOREIGN KEY (supplier_id) REFERENCES suppliers(id);

CREATE TABLE product_categories (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR,
    department VARCHAR,
    description VARCHAR
);

ALTER TABLE ONLY products
    ADD CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES product_categories(id);

CREATE TABLE order_header (
    id SERIAL PRIMARY KEY NOT NULL,
    order_date DATE,
    order_total NUMERIC
);

CREATE TABLE order_items (
    id SERIAL PRIMARY KEY NOT NULL,
    order_id INTEGER,
    product_id INTEGER,
    quantity INTEGER
);

ALTER TABLE ONLY order_items
    ADD CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES order_header(id);

ALTER TABLE ONLY order_items
    ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES products(id);
