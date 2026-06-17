-- V2__create_expense.sql
CREATE TABLE expense (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    cost DOUBLE NOT NULL CHECK (cost > 0),
    expense_date DATE NOT NULL,
    category_id BIGINT NOT NULL,
    CONSTRAINT fk_expense_category FOREIGN KEY (category_id)
        REFERENCES category(id)
        ON DELETE CASCADE
);
