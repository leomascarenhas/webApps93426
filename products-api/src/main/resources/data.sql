-- Insert initial products into the H2 database
INSERT INTO product (id, description, price, category, version, created_at, updated_at) VALUES
                                                                                            (1, 'Product A', 19.99, 'Category A', 0, CURRENT_TIMESTAMP, NULL),
                                                                                            (2, 'Product B', 29.99, 'Category B', 0, CURRENT_TIMESTAMP, NULL),
                                                                                            (3, 'Product C', 39.99, 'Category C', 0, CURRENT_TIMESTAMP, NULL);
