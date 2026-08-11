-- V3__create_goal.sql
CREATE TABLE goal (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    month INT NOT NULL,
    year INT NOT NULL,
    ceiling DOUBLE NOT NULL CHECK (ceiling > 0)
);

-- Tabela de junção entre metas e despesas
CREATE TABLE goal_expense (
    goal_id BIGINT NOT NULL,
    expense_id BIGINT NOT NULL,
    linked_at DATE DEFAULT CURRENT_DATE,
    PRIMARY KEY (goal_id, expense_id),
    CONSTRAINT fk_goal FOREIGN KEY (goal_id) REFERENCES goal(id) ON DELETE CASCADE,
    CONSTRAINT fk_expense FOREIGN KEY (expense_id) REFERENCES expense(id) ON DELETE CASCADE
);
