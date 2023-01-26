DROP TABLE IF EXISTS products CASCADE;
DROP TABLE IF EXISTS suppliers;
DROP TABLE IF EXISTS product_categories;
DROP TABLE IF EXISTS order_header CASCADE ;
DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS carts;

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

CREATE TABLE users (
    id SERIAL PRIMARY KEY NOT NULL,
    username VARCHAR,
    email VARCHAR,
    password VARCHAR,
    isAdmin int default 0
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
    order_total NUMERIC,
    user_id INTEGER
);

ALTER TABLE ONLY order_header
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id);

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

CREATE TABLE carts(
    id SERIAL PRIMARY KEY NOT NULL,
    user_id INTEGER,
    product_id INTEGER,
    quantity INTEGER
);

ALTER TABLE ONLY carts
    ADD CONSTRAINT fk_carts_user_id FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE ONLY carts
    ADD CONSTRAINT fk_carts_product_id FOREIGN KEY (product_id) REFERENCES products(id);

INSERT INTO product_categories(name) VALUES ('Japanese_demons');
INSERT INTO product_categories(name) VALUES ('Cursed_item');
INSERT INTO product_categories(name) VALUES ('legendaryCreatures');

INSERT INTO suppliers(name) VALUES ('Japan');
INSERT INTO suppliers(name) VALUES ('DemonTrader');
INSERT INTO suppliers(name) VALUES ('nationalCreatureAgency');

INSERT INTO products (name, price, category_id, supplier_id, currency, description, image_file_name) VALUES ('Aka - Shita', 49.9, 1, 1, 'USD', 'Aka shita is a mysterious spirit which takes the form of a dark cloud with sharp claws, and a hairy, bestial face. The aka shita appears during the summer months, when rain and water are at their highest demand to ensure a successful growing season. They are agents of retribution, primarily known as punishers in water disputes.Some clever water thieves are never caught, and may think they ve gotten away with their crime. So this is a Japanese Storm demon.', 'product_1');
INSERT INTO products (name, price, category_id, supplier_id, currency, description, image_file_name) VALUES ('Kitsune', 89, 1, 1, 'USD', 'According to yōkai folklore, all foxes have the ability to shapeshift into human form. While some folktales speak of kitsune employing this ability to trick others—as foxes in folklore often do—other stories portray them as faithful guardians, friends, and lovers.' , 'product_3');
INSERT INTO products (name, price, category_id, supplier_id, currency, description, image_file_name) VALUES ('Jorogumo', 99, 1, 1, 'USD', 'It can shapeshift into a beautiful woman, so the kanji that represent its actual meaning are 女郎蜘蛛 (lit. "woman-spider"); the kanji which are used to write it instead, 絡新婦 (lit. "entangling newlywed woman") have a jukujikun pronunciation that is related to the meaning, but not the sound of the word. In Toriyama Sekien"s Gazu Hyakki Yagyō, it is depicted as a spider woman manipulating small fire-breathing spiders.' , 'product_4');
INSERT INTO products (name, price, category_id, supplier_id, currency, description, image_file_name) VALUES ('AnnaBelle', 47.9, 2, 2, 'USD', 'Annabelle is an allegedly haunted Raggedy Ann doll, housed in the (now closed) occult museum of the paranormal investigators Ed and Lorraine Warren. Annabelle was moved there after supposed hauntings in 1970. A character based on the doll is one of the antagonists that appear in the Conjuring Universe.' , 'product_2');
INSERT INTO products (name, price, category_id, supplier_id, currency, description, image_file_name) VALUES ('Cursed Mirror', 99, 2, 2, 'USD', 'Myrtles Plantation in Louisiana, USA is said to be one of the most haunted places in the world. However, the most spooky item in the house is a mirror. Locals claim that the mirror is cursed and has the spirits of Sara Woodruff and her two children who were poisoned by their slave Chloe trapped inside it.' , 'product_5');
INSERT INTO products (name, price, category_id, supplier_id, currency, description, image_file_name) VALUES ('The anguished man painting', 99, 2, 2, 'USD', 'The Anguished Man is a painting created by an unknown artist.[1][2] Owner Sean Robinson claims to have inherited the painting from his grandmother, who told him that the artist who created the painting had mixed his own blood into the paint and committed suicide soon after finishing the work.The painting has been characterized as being supposedly haunted.', 'product_6');
INSERT INTO products (name, price, category_id, supplier_id, currency, description, image_file_name) VALUES ('Fenrir', 120, 3, 3, 'USD', 'In the Prose Edda, additional information is given about Fenrir, including that, due to the gods" knowledge of prophecies foretelling ..."', 'product_7');
INSERT INTO products (name, price, category_id, supplier_id, currency, description, image_file_name) VALUES ('Kraken', 1000, 3, 3, 'USD', 'The legend of the Kraken may have originated from sightings of giant squid, which may grow to 12–15 m (40–50 feet) in length. ...', 'product_8');