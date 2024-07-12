-- Optimizar inserciones en 'categories'
INSERT INTO categories (name, description)
VALUES ('Keyboards', 'Computer Keyboards'),
       ('Monitors', 'Computer Monitors'),
       ('Screens', 'Display Screens'),
       ('Mice', 'Computer Mice'),
       ('Accessories', 'Computer Accessories');

-- Inserciones optimizadas en 'products' asumiendo IDs secuenciales para 'categories'
-- Para 'Keyboards' (asumiendo ID 1)
INSERT INTO products (name, description, available_quantity, price, category_id)
VALUES ('Mechanical Keyboard 1', 'Mechanical keyboard with RGB lighting', 10, 99.99, 1),
       ('Wireless Compact Keyboard 1', 'Wireless compact keyboard', 15, 79.99, 1),
       ('Gaming Keyboard 1', 'Backlit gaming keyboard with customizable keys', 20, 129.99, 1),
       ('Ergonomic Keyboard 1', 'Mechanical keyboard with wrist rest', 25, 109.99, 1),
       ('Wireless Combo 1', 'Wireless keyboard and mouse combo', 18, 69.99, 1);

-- Para 'Monitors' (asumiendo ID 2)
INSERT INTO products (name, description, available_quantity, price, category_id)
VALUES ('4K Monitor 1', '27-inch IPS monitor with 4K resolution', 30, 399.99, 2),
       ('Ultra-wide Gaming Monitor 1', 'Ultra-wide gaming monitor with HDR support', 25, 499.99, 2),
       ('Office Monitor 1', '24-inch LED monitor for office use', 22, 179.99, 2),
       ('Curved Monitor 1', '32-inch curved monitor with AMD FreeSync', 28, 329.99, 2),
       ('Portable Monitor 1', 'Portable USB-C monitor for laptops', 35, 249.99, 2);

-- Para 'Screens' (asumiendo ID 3)
INSERT INTO products (name, description, available_quantity, price, category_id)
VALUES ('Curved OLED Gaming Screen 1', 'Curved OLED gaming screen with 240Hz refresh rate', 15, 799.99, 3),
       ('QLED Monitor 1', 'Flat QLED monitor with 1440p resolution', 18, 599.99, 3),
       ('Touch Screen Display 1', '27-inch touch screen display for creative work', 22, 699.99, 3),
       ('Ultra-slim 4K HDR Display 1', 'Ultra-slim 4K HDR display for multimedia', 20, 449.99, 3),
       ('Gaming Projector 1', 'Gaming projector with low input lag', 25, 899.99, 3);

-- Para 'Mice' (asumiendo ID 4)
INSERT INTO products (name, description, available_quantity, price, category_id)
VALUES ('RGB Gaming Mouse 1', 'Wireless gaming mouse with customizable RGB lighting', 30, 59.99, 4),
       ('Ergonomic Wired Mouse 1', 'Ergonomic wired mouse for productivity', 28, 29.99, 4),
       ('Ambidextrous Gaming Mouse 1', 'Ambidextrous gaming mouse with high DPI', 32, 69.99, 4),
       ('Travel Mouse 1', 'Travel-sized compact mouse for laptops', 26, 19.99, 4),
       ('Vertical Ergonomic Mouse 1', 'Vertical ergonomic mouse for reduced strain', 35, 39.99, 4);

-- Para 'Accessories' (asumiendo ID 5)
INSERT INTO products (name, description, available_quantity, price, category_id)
VALUES ('Adjustable Laptop Stand 1', 'Adjustable laptop stand with cooling fan', 25, 34.99, 5),
       ('Wireless Charging Pad 1', 'Wireless charging pad for smartphones', 20, 24.99, 5),
       ('RGB Headset Stand 1', 'Gaming headset stand with RGB lighting', 28, 49.99, 5),
       ('Bluetooth Keypad 1', 'Bluetooth mechanical keypad for tablets', 22, 39.99, 5),
       ('External Hard Drive Enclosure 1', 'External hard drive enclosure with USB-C', 30, 29.99, 5);