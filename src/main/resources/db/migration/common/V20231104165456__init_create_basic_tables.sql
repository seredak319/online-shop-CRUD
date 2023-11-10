CREATE SCHEMA IF NOT EXISTS dictionary;
CREATE TABLE dictionary.order_details
(
    status_id    INT PRIMARY KEY UNIQUE,
    order_status VARCHAR(20) NOT NULL UNIQUE
);

INSERT INTO dictionary.order_details (status_id, order_status)
VALUES (1, 'NEW'),
       (2, 'PROCESSING'),
       (3, 'FINISHED'),
       (4, 'CANCELED');

CREATE SCHEMA IF NOT EXISTS entities;
CREATE TABLE entities.Clients
(
    client_id  INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50)         NOT NULL,
    last_name  VARCHAR(50)         NOT NULL,
    email      VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE entities.Products
(
    product_id     INT PRIMARY KEY AUTO_INCREMENT,
    name           VARCHAR(100)   NOT NULL,
    price          DECIMAL(10, 2) NOT NULL,
    stock_quantity INT            NOT NULL,
    product_code   VARCHAR(100) UNIQUE
);

CREATE SCHEMA IF NOT EXISTS transactions;
CREATE TABLE transactions.Orders
(
    order_id    INT NOT NULL,
    client_id   INT NOT NULL,
    orderStatus VARCHAR(30),
    FOREIGN KEY (client_id) REFERENCES entities.Clients (client_id) ON DELETE CASCADE
);

CREATE TABLE transactions.Ordered_Products
(
    order_product_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id         INT NOT NULL,
    product_code     VARCHAR(100),
    quantity         INT NOT NULL,
    FOREIGN KEY (product_code) REFERENCES entities.Products (product_code) ON DELETE CASCADE
);
