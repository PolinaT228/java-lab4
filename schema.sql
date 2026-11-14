CREATE TABLE IF NOT EXISTS clothes (
                                       id SERIAL PRIMARY KEY,
                                       type VARCHAR(50) NOT NULL,
    name VARCHAR(255) NOT NULL,
    size VARCHAR(50) NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    quantity INT NOT NULL,
    category VARCHAR(50) NOT NULL,
    has_belt BOOLEAN,
    sleeve_length VARCHAR(50),
    has_hood BOOLEAN,
    material VARCHAR(50)
    );