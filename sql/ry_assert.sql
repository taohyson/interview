-- 删除名为 person 的表（如果存在）
DROP TABLE IF EXISTS person;

-- 创建 person 表
CREATE TABLE person (
                        id INT PRIMARY KEY,
                        account VARCHAR(255) NOT NULL,
                        name VARCHAR(255) NOT NULL
);

-- 插入一条记录
INSERT INTO person (id, account, name) VALUES (1, 'christine001', 'christine hsieh');


-- 删除名为 anAssert 的表（如果存在）
DROP TABLE IF EXISTS anAssert;

-- 创建 anAssert 表
CREATE TABLE anAssert (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       person_id INT,
                       symbol VARCHAR(255) NOT NULL,
                       name VARCHAR(255) NOT NULL,
                       currency VARCHAR(255) NOT NULL,
                       amount DECIMAL(10, 2) NOT NULL,
                       last_price DECIMAL(10, 2) NOT NULL,
                       FOREIGN KEY (person_id) REFERENCES person(id)
);

-- 插入一条记录
INSERT INTO anAssert (person_id, symbol, name, currency, amount, last_price)
VALUES (1, '2317.TW', '金', 'TWD', 100, 203);
