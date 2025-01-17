CREATE TABLE IF NOT EXISTS Product (
                                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                       description VARCHAR(255) NOT NULL,
                                       price DECIMAL(10, 2) NOT NULL,
                                       category VARCHAR(100) NOT NULL,
                                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS Tag (
                                   id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                   name VARCHAR(100) NOT NULL UNIQUE,
                                   description VARCHAR(255),
                                   product_id BIGINT NOT NULL,
                                   FOREIGN KEY (product_id) REFERENCES Product(id) ON DELETE CASCADE
);

