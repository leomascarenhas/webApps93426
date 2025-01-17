-- Insert sample products
INSERT INTO Product (description, price, category, created_at, updated_at)
VALUES
    ('Smartphone', 699.99, 'Electronics', '2025-01-01 10:00:00', '2025-01-01 10:00:00'),
    ('Laptop', 1299.99, 'Electronics', '2025-01-02 10:00:00', '2025-01-02 10:00:00'),
    ('Coffee Maker', 49.99, 'Home Appliances', '2025-01-03 10:00:00', '2025-01-03 10:00:00'),
    ('Blender', 89.99, 'Home Appliances', '2025-01-04 10:00:00', '2025-01-04 10:00:00');

-- -- Insert sample tags for products
INSERT INTO Tag (name, description, product_id)
VALUES
    ('Android', 'Tag for Android products', 1),
    ('5G', 'Tag for 5G compatibility', 1),
    ('Gaming', 'Tag for gaming laptops', 2),
    ('Touchscreen', 'Tag for touchscreen devices', 2),
    ('Kitchen', 'Tag for kitchen appliances', 3),
    ('Smoothies', 'Tag for smoothie makers', 4);
