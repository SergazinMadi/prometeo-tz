CREATE TABLE IF NOT EXISTS orders (
    id UUID PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    currency VARCHAR(3) NOT NULL
);
