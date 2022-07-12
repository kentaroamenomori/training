-- 部署のテーブルを作成
CREATE TABLE divisions(id text PRIMARY KEY, name text NOT NULL);

-- 従業員のテーブルを作成
CREATE TABLE employees(id integer PRIMARY KEY, name text NOT NULL, date_joined date NOT NULL, phone_number text);
