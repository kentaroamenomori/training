-- 事務所テーブルを作成
CREATE TABLE offices(
	id INTEGER NOT NULL, 
	name TEXT NOT NULL, 
	address TEXT
);

-- 従業員テーブルに勤務先事務所IDカラムを追加
ALTER TABLE employees ADD COLUMN office_id INTEGER;

-- 外部キー制約を追加
ALTER TABLE employees
	ADD CONSTRAINT office_id
	FOREIGN KEY(office_id) REFERENCES offices(id);
