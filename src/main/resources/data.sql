
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');


INSERT INTO users (username, email, password, name, surname, user_phone_number) 
VALUES ('testUser', 'test@test.com', '123456', 'test', 'test', 123456789);

INSERT INTO user_roles (user_id, role_id) 
VALUES ((SELECT id FROM users WHERE username = 'testUser'), 1),
       ((SELECT id FROM users WHERE username = 'testUser'), 2),
       ((SELECT id FROM users WHERE username = 'testUser'), 3);


INSERT INTO types (type_name) VALUES ('A51');
INSERT INTO types (type_name) VALUES ('Mate20Lite');
INSERT INTO types (type_name) VALUES ('RedmiNote11');

INSERT INTO devices (customer_phone_number, customer_name, date_of_receipt, repaired, type_id) VALUES (404040404, 'Lenny', CURRENT_TIMESTAMP, false, 1);
INSERT INTO devices (customer_phone_number, customer_name, date_of_receipt, repaired, type_id) VALUES (404040404, 'Alice', CURRENT_TIMESTAMP, false, 1);
INSERT INTO devices (customer_phone_number, customer_name, date_of_receipt, repaired, type_id) VALUES (404040404, 'Bob', CURRENT_TIMESTAMP, false, 1);

INSERT INTO devices (customer_phone_number, customer_name, date_of_receipt, repaired, type_id) VALUES (404040404, 'Carol', CURRENT_TIMESTAMP, false, 2);
INSERT INTO devices (customer_phone_number, customer_name, date_of_receipt, repaired, type_id) VALUES (404040404, 'David', CURRENT_TIMESTAMP, false, 2);
INSERT INTO devices (customer_phone_number, customer_name, date_of_receipt, repaired, type_id) VALUES (404040404, 'Emily', CURRENT_TIMESTAMP, false, 2);

INSERT INTO devices (customer_phone_number, customer_name, date_of_receipt, repaired, type_id) VALUES (404040404, 'Fiona', CURRENT_TIMESTAMP, false, 3);
INSERT INTO devices (customer_phone_number, customer_name, date_of_receipt, repaired, type_id) VALUES (404040404, 'George', CURRENT_TIMESTAMP, false, 3);
INSERT INTO devices (customer_phone_number, customer_name, date_of_receipt, repaired, type_id) VALUES (404040404, 'Hannah', CURRENT_TIMESTAMP, false, 3);


INSERT INTO elements (quantity, critical_quantity, element_name, element_price, type_id) VALUES (10, 5, 'Speaker', 250.00, 1);
INSERT INTO elements (quantity, critical_quantity, element_name, element_price, type_id) VALUES (20, 10, 'Battery', 50.00, 1);
INSERT INTO elements (quantity, critical_quantity, element_name, element_price, type_id) VALUES (15, 7, 'Screen', 300.00, 1);
INSERT INTO elements (quantity, critical_quantity, element_name, element_price, type_id) VALUES (8, 4, 'Camera', 150.00, 1);
INSERT INTO elements (quantity, critical_quantity, element_name, element_price, type_id) VALUES (25, 12, 'Charger', 20.00, 1);

INSERT INTO elements (quantity, critical_quantity, element_name, element_price, type_id) VALUES (12, 6, 'Speaker', 280.00, 2);
INSERT INTO elements (quantity, critical_quantity, element_name, element_price, type_id) VALUES (18, 9, 'Battery', 60.00, 2);
INSERT INTO elements (quantity, critical_quantity, element_name, element_price, type_id) VALUES (10, 5, 'Screen', 320.00, 2);
INSERT INTO elements (quantity, critical_quantity, element_name, element_price, type_id) VALUES (6, 3, 'Camera', 180.00, 2);
INSERT INTO elements (quantity, critical_quantity, element_name, element_price, type_id) VALUES (22, 11, 'Charger', 25.00, 2);

INSERT INTO elements (quantity, critical_quantity, element_name, element_price, type_id) VALUES (15, 8, 'Speaker', 300.00, 3);
INSERT INTO elements (quantity, critical_quantity, element_name, element_price, type_id) VALUES (22, 11, 'Battery', 70.00, 3);
INSERT INTO elements (quantity, critical_quantity, element_name, element_price, type_id) VALUES (17, 9, 'Screen', 340.00, 3);
INSERT INTO elements (quantity, critical_quantity, element_name, element_price, type_id) VALUES (10, 5, 'Camera', 200.00, 3);
INSERT INTO elements (quantity, critical_quantity, element_name, element_price, type_id) VALUES (30, 15, 'Charger', 30.00, 3);

INSERT INTO notes (description, date_created, user_id) VALUES ('Hello User!', CURRENT_TIMESTAMP, NULL);
