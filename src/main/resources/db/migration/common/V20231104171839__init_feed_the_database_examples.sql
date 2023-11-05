INSERT INTO entities.Clients (first_name, last_name, email)
VALUES ('John', 'Doe', 'john.doe@example.com'),
       ('Alice', 'Smith', 'alice.smith@example.com'),
       ('Bob', 'Johnson', 'bob.johnson@example.com'),
       ('Sarah', 'Wilson', 'sarah.wilson@example.com'),
       ('Michael', 'Brown', 'michael.brown@example.com');

INSERT INTO entities.Products (name, price, stock_quantity, ProductCode)
VALUES ('USB Drive', 9.99, 100, 'PRD-00001-USBDriveCode'),
       ('Wireless Mouse', 14.99, 75, 'PRD-00002-WirelessMouseCode'),
       ('Smartphone', 499.99, 50, 'PRD-00003-SmartphoneCode'),
       ('Power Drill', 129.99, 120, 'PRD-00004-PowerDrillCode'),
       ('Headphones', 59.99, 200, 'PRD-00005-HeadphonesCode'),
       ('Iphone 15', 2199.00, 0, 'PRD-00006-Ip15');


INSERT INTO transactions.Orders (client_id, order_status)
VALUES (1, 1),
       (2, 1),
       (3, 2),
       (4, 3),
       (5, 2);

INSERT INTO Ordered_Products (order_id, product_id, quantity)
VALUES (1, 1, 5),
       (1, 3, 2),
       (2, 2, 3),
       (3, 4, 1),
       (4, 5, 10),
       (5, 1, 8),
       (5, 2, 5);