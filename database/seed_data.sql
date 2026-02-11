USE smart_billing;

-- password: admin123
INSERT INTO users (username, password, role, salary)
VALUES
('admin', '$2a$10$5yJ8SikM6Lt4s46HoPazTu9RI4T5Q5f4fR1W2M1QyyNwQmQf8QmXW', 'ADMIN', 50000.00),
('cashier1', '$2a$10$5yJ8SikM6Lt4s46HoPazTu9RI4T5Q5f4fR1W2M1QyyNwQmQf8QmXW', 'CASHIER', 25000.00);

INSERT INTO customers (first_name, middle_name, last_name, phone_number, total_visits, last_visit)
VALUES
('John', 'A', 'Doe', '9876543210', 3, CURDATE()),
('Priya', NULL, 'Sharma', '9898989898', 1, CURDATE());

INSERT INTO products (product_name, category, price, quantity)
VALUES
('Notebook', 'Stationery', 55.00, 40),
('Pen', 'Stationery', 12.00, 200),
('Mouse', 'Electronics', 450.00, 15);
