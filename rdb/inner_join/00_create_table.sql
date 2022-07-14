-- 部署のテーブルを作成
CREATE TABLE divisions(id TEXT PRIMARY KEY, name TEXT NOT NULL);

-- 従業員のテーブルを作成
CREATE TABLE employees(
	id INTEGER PRIMARY KEY, 
	name TEXT NOT NULL, 
	date_joined DATE NOT NULL, 
	phone_number TEXT
);
