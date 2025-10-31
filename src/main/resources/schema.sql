CREATE TABLE themes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(500)
);

CREATE TABLE words (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    word VARCHAR(50) NOT NULL,
    theme_id BIGINT NOT NULL,
    FOREIGN KEY (theme_id) REFERENCES themes(id)
);

CREATE TABLE games (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    target_word VARCHAR(50) NOT NULL,
    theme_id BIGINT NOT NULL,
    status VARCHAR(20) NOT NULL,
    max_attempts INT NOT NULL DEFAULT 6,
    created_at TIMESTAMP NOT NULL,
    completed_at TIMESTAMP NULL,
    FOREIGN KEY (theme_id) REFERENCES themes(id)
);

CREATE TABLE attempts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    game_id BIGINT NOT NULL,
    attempt_number INT NOT NULL,
    word_attempt VARCHAR(50) NOT NULL,
    feedback VARCHAR(500) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    FOREIGN KEY (game_id) REFERENCES games(id)
);