CREATE TABLE tb_users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255)
);


CREATE TABLE tb_tasks (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    status VARCHAR(255),
    user_id INTEGER,
    description VARCHAR(255),
    created_at DATE,
    updated_at DATE,
    FOREIGN KEY (user_id) REFERENCES tb_users(id)
);
