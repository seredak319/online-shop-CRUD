INSERT INTO entities.Clients (first_name, last_name, email)
VALUES ('John', 'Doe', 'john.doe@example.com'),
       ('Alice', 'Smith', 'alice.smith@example.com'),
       ('Bob', 'Johnson', 'bob.johnson@example.com'),
       ('Sarah', 'Wilson', 'sarah.wilson@example.com'),
       ('Michael', 'Brown', 'michael.brown@example.com');

INSERT INTO entities.Products (name, price, stock_quantity, product_code)
VALUES ('USB Drive', 9.99, 100, 'PRD-00001-USBDriveCode'),
       ('Wireless Mouse', 14.99, 75, 'PRD-00002-WirelessMouseCode'),
       ('Smartphone', 499.99, 50, 'PRD-00003-SmartphoneCode'),
       ('Power Drill', 129.99, 120, 'PRD-00004-PowerDrillCode'),
       ('Headphones', 59.99, 200, 'PRD-00005-HeadphonesCode'),
       ('Iphone 15', 2199.00, 0, 'PRD-00006-Ip15');


INSERT INTO transactions.Orders (order_id, client_id, orderStatus)
VALUES (0, 1, 'NEW'),
       (1, 2, 'NEW'),
       (2, 3, 'CANCELED'),
       (3, 4, 'FINISHED'),
       (4, 5, 'PROCESSING');

INSERT INTO transactions.Ordered_Products (order_id, product_code, quantity)
VALUES (1, 'PRD-00001-USBDriveCode', 5),
       (1, 'PRD-00003-SmartphoneCode', 2),
       (2, 'PRD-00002-WirelessMouseCode', 3),
       (3, 'PRD-00004-PowerDrillCode', 1),
       (4, 'PRD-00005-HeadphonesCode', 10),
       (5, 'PRD-00001-USBDriveCode', 8),
       (5, 'PRD-00002-WirelessMouseCode', 5);