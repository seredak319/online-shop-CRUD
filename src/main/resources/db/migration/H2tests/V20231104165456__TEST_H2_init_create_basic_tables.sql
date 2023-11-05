CREATE SCHEMA IF NOT EXISTS dictionary;
CREATE TABLE dictionary.order_details
(
    status_id    INT PRIMARY KEY UNIQUE,
    order_status VARCHAR(20) NOT NULL UNIQUE
);

INSERT INTO dictionary.order_details (status_id, order_status)
VALUES (1, 'NEW'),
       (2, 'PROCESSING'),
       (3, 'FINISHED');

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
    ProductCode    VARCHAR(100) UNIQUE
);

CREATE SCHEMA IF NOT EXISTS transactions;
CREATE TABLE transactions.Orders
(
    order_id     INT PRIMARY KEY AUTO_INCREMENT,
    client_id    INT NOT NULL,
    order_status INT NOT NULL,
    FOREIGN KEY (client_id) REFERENCES entities.Clients (client_id),
    FOREIGN KEY (order_status) REFERENCES dictionary.order_details (status_id)
);

CREATE TABLE Ordered_Products
(
    order_product_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id         INT NOT NULL,
    product_id       INT NOT NULL,
    quantity         INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES transactions.Orders (order_id),
    FOREIGN KEY (product_id) REFERENCES entities.Products (product_id)
);
